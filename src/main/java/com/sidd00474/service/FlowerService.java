package com.sidd00474.service;

import com.sidd00474.entity.Flower;
import com.sidd00474.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class FlowerService {
    @Autowired
    private FlowerRepository flowerRepository;
    //list.
    public List<Flower> getListByStatus(){
        return flowerRepository.findAllByStatus(1);
    }

    //search by name.
    public List<Flower> getListByName(String name){
        return flowerRepository.findAllByNameAndStatus(name,1);
    }
    //add.
    public Flower addFlower(Flower flower){
        flower.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        flower.setStatus(1);
        flowerRepository.save(flower);
        return flower;
    }
    //get detail.
    public Flower getDetailFlower(long id){
        return flowerRepository.findById(id).orElse(null);
    }

    //edit.
    public Flower editFlower(long id,Flower updateFlower){
        Optional<Flower> optionalFlower = flowerRepository.findById(id);
        if(optionalFlower.isPresent()){
            Flower existFlower = optionalFlower.get();
            existFlower.setName(updateFlower.getName());
            existFlower.setDescription(updateFlower.getDescription());
            existFlower.setPrice(updateFlower.getPrice());
            existFlower.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            flowerRepository.save(existFlower);
            return existFlower;
        }
        return null;
    }
    //delete.
    public Flower deleteFlower(long id){
        Optional<Flower> optionalFlower = flowerRepository.findById(id);
        if(optionalFlower.isPresent()){
            Flower existFlower = optionalFlower.get();
            existFlower.setStatus(0);
            existFlower.setDeletedAt(Calendar.getInstance().getTimeInMillis());
            flowerRepository.save(existFlower);
            return existFlower;
        }
        return null;
    }
}
