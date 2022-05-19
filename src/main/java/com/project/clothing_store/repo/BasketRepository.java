package com.project.clothing_store.repo;

import com.project.clothing_store.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

    Basket findByUserIdAndItemId(int userId, int itemId);

    List<Basket> findAllByUserId(int userId);

    Basket findById(int id);

    Long deleteById(int id);

    @Transactional
    Long deleteAllByUserId(int userId);
}
