package com.performancemeasurements.performance_measurement.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performancemeasurements.performance_measurement.entities.Entrenamiento;
import java.util.List;
import java.time.LocalDate;


public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Integer>{
    List<Entrenamiento> findByFecha(LocalDate fecha);
}
