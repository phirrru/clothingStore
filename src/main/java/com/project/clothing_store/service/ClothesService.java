package com.project.clothing_store.service;


import com.project.clothing_store.models.Clothes;
import com.project.clothing_store.repo.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothesService {

    private ClothesRepository clothesRepository;

    @Autowired
    public ClothesService(ClothesRepository clothesRepository){
        this.clothesRepository = clothesRepository;
    }

    public List<Clothes> getAllItemsById(){
        return clothesRepository.findAll();
    }

    public List<Clothes> getAllItemsByTypeId(int typeId) {
        return clothesRepository.findAllByTypeId(typeId);
    }

    public Clothes getItemById(int id){
        return clothesRepository.findById(id);
    }

    public void saveItem(Clothes items){
        clothesRepository.save(items);
    }

    public void deleteItemById(int id){
        clothesRepository.deleteById(id);
    }
}
