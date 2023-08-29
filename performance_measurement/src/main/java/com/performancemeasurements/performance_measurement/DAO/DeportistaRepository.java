package com.performancemeasurements.performance_measurement.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performancemeasurements.performance_measurement.entities.Deportista;

public interface DeportistaRepository extends JpaRepository<Deportista, Integer> {
    
}
