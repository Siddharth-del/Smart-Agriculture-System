package com.SmartAgriculture.Cropp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SmartAgriculture.Cropp.model.DiseaseDetection;

@Repository
public interface DiseaseRepository extends JpaRepository<DiseaseDetection,Long>{
    
}
