package com.example.tptekup.MvcControllers;
import com.example.tptekup.JWT.JwtUtils;
import com.example.tptekup.Repositories.UserRepository;
import com.example.tptekup.Request.LoginRequest;
import com.example.tptekup.Request.SignupRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
public String signin(Model model) {
            LoginRequest loginRequest=new LoginRequest() ;
            model.addAttribute("loginRequest",loginRequest);
    return "Authentification/login";
}
@PostMapping("/login")
 public String signinpost(Model model, @ModelAttribute LoginRequest loginRequest, HttpSession session, HttpServletResponse res) {
    try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    } catch (UsernameNotFoundException e) {

        session.setAttribute("msg","Bad Credentials");
        return "redirect:/login";

    } catch(BadCredentialsException e)
    {
        session.setAttribute("msg","Bad Credentials");
        return "redirect:/login";
    }
    // fine area..
    try {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        System.out.println("userDetails.getUsername: "   +userDetails.getUsername());
        final String token =	jwtUtils.generateJwtToken(userDetails);
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(Integer.MAX_VALUE);
        res.addCookie(cookie);
        System.out.println("token: " + token);
        return "redirect:/allBrands";
       }catch(Exception e)
      {
        session.setAttribute("msg","Credentials were right But something went wrong!!");
        return "redirect:/login";
      }
      }

 /*@PostMapping("/signup")
 public String signupPost(Model model, @ModelAttribute SignupRequest registrationRequest, HttpSession session, HttpServletResponse res) {
        try {
            // Validate the registration request (optional)
            if (registrationRequest.getUsername() == null || registrationRequest.getPassword() == null || registrationRequest.getEmail() == null) {
                session.setAttribute("msg", "All fields are required.");
                return "redirect:/signup";
            }

            // Check if the username already exists
            if ((userDetailsService.usernameExists(registrationRequest.getUsername())) {
                session.setAttribute("msg", "Username already taken.");
                return "redirect:/signup";
            }

            // Save user details (make sure the password is hashed before saving)
            User newUser = new User();
            newUser.setUsername(registrationRequest.getUsername());
            newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword())); // Hash the password
            newUser.setEmail(registrationRequest.getEmail());
            // Save to the database (ensure userRepository.save() or equivalent is correctly implemented)
            userRepository.save(newUser);

            // After successful registration, redirect to the login page
            session.setAttribute("msg", "Registration successful! Please login.");
            return "redirect:/login"; // Redirect to login after successful signup
        } catch (Exception e) {
            // Handle any errors (e.g., database issues)
            session.setAttribute("msg", "Something went wrong during registration. Please try again.");
            logger.error("Error during signup: " + e.getMessage());
            return "redirect:/signup"; // Return to signup page if there was an error
        }
    }



 @GetMapping("/allBrands")
    public String logout(HttpServletRequest request, HttpServletResponse res, Model m, HttpSession session) {
        String msg = null;

        Cookie[] cookies2 =request.getCookies();
        for(int i = 0; i < cookies2.length; i++)
        {
            if (cookies2[i].getName().equals("token"))
            {
                cookies2[i].setMaxAge(0);
                res.addCookie(cookies2[i]);
                msg = "Logout successfully";

            }

        }
        session.setAttribute("msg", msg);


        return "redirect:/login";

    }*/
}
