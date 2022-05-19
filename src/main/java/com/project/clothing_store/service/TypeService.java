package com.project.clothing_store.service;

import com.project.clothing_store.models.Type;
import com.project.clothing_store.repo.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {

    private TypeRepository typeRepo;

    @Autowired
    public TypeService( TypeRepository typeRepo){
        this.typeRepo = typeRepo;
    }

    public Type getTypeById(int id){
        return typeRepo.findById(id);
    }

    public List<Type> getAllTypes() {
        return typeRepo.findAll();
    }

    public void saveType(Type type) {
        typeRepo.save(type);
    }

    public void deleteTypeById(int id) {
        typeRepo.deleteById(id);
    }
}
