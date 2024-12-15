package com.example.tptekup.Repositories;

import com.example.tptekup.Entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartitemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByClientId(long clientId);

    List<CartItem> findByProductId(long productId);
}
