package com.example.tptekup.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users", // Specify the table name
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"), // Unique constraint for 'username'
                @UniqueConstraint(columnNames = "email")     // Unique constraint for 'email'
        }
)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 20)
    private String username;


    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;


    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name="user_id"),
               inverseJoinColumns=@JoinColumn (name="role_ic"))
    private Set<Role> roles= new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User() {
    }

    public @NotBlank @Size(max = 20) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(max = 20) String username) {
        this.username = username;
    }

    public @NotBlank @Size(max = 50) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Size(max = 50) String email) {
        this.email = email;
    }

    public @NotBlank @Size(max = 120) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(max = 120) String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
