package com.project.clothing_store.repo;

import com.project.clothing_store.models.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {

    Favorites findByUserIdAndItemId(int userId, int itemId);

    List<Favorites> findAllByUserId(int userId);

    Favorites findById(int id);

    Long deleteById(int id);

    @Transactional
    Long deleteAllByUserId(int userId);
}