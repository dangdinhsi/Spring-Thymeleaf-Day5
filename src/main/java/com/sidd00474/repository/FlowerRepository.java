package com.sidd00474.repository;

import com.sidd00474.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlowerRepository extends JpaRepository<Flower,Long> {
    List<Flower> findAllByStatus(int status);
    List<Flower> findAllByNameAndStatus(String name,int status); // search by name
}
