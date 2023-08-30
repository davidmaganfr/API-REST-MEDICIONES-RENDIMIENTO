package com.performancemeasurements.performance_measurement.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performancemeasurements.performance_measurement.entities.Antropometria;
import java.time.LocalDate;
import java.util.List;



public interface AntropometriaRepository extends JpaRepository<Antropometria, Integer> {
    List<Antropometria> findByFecha(LocalDate fecha);
}
