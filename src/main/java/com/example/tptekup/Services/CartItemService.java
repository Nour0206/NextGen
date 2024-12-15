package com.example.tptekup.Services;

import com.example.tptekup.Entities.CartItem;
import com.example.tptekup.Repositories.CartitemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartitemRepository cartItemRepository;

    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public CartItem getCartItemById(long id) {
        return cartItemRepository.findById(id).get();
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public void deleteCartItem(long id) {
        cartItemRepository.deleteById(id);
    }

    public CartItem updateCartItem(CartItem cartItem) {
        return cartItemRepository.saveAndFlush(cartItem);
    }

    public List<CartItem> getCartItemsByClientId(long clientId) {
        return cartItemRepository.findByClientId(clientId);
    }

    public List<CartItem> getCartItemsByProductId(long productId) {
        return cartItemRepository.findByProductId(productId);
    }
}
