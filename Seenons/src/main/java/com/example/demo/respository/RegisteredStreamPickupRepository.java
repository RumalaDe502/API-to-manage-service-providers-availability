package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.RegisterStreamPickup;

@Repository
@Transactional
public interface RegisteredStreamPickupRepository extends JpaRepository<RegisterStreamPickup, Integer> {
}
