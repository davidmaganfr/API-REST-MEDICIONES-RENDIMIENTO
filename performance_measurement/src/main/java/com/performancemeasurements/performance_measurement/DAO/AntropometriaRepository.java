package com.performancemeasurements.performance_measurement.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performancemeasurements.performance_measurement.entities.Antropometria;

public interface AntropometriaRepository extends JpaRepository<Antropometria, Integer> {
    
}
