package com.project.clothing_store.repo;

import com.project.clothing_store.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    Type findById(int id);
    Long deleteById(int id);
}
