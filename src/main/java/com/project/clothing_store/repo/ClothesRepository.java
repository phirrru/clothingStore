package com.project.clothing_store.repo;

import com.project.clothing_store.models.Clothes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClothesRepository extends CrudRepository<Clothes, Long> {
    List<Clothes> findAllByTypeId(int typeId);

    List <Clothes> findAll();


    Clothes findById(int id);

//    @Override
//    Optional findById(int id);

    Long deleteById(int id);
}
