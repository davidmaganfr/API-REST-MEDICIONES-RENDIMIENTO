package com.performancemeasurements.performance_measurement.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performancemeasurements.performance_measurement.entities.Entrenamiento;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Integer>{
    
}
