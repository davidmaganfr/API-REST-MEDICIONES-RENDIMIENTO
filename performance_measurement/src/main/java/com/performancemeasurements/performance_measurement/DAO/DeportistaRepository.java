package com.performancemeasurements.performance_measurement.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performancemeasurements.performance_measurement.entities.Deportista;
import java.util.List;


public interface DeportistaRepository extends JpaRepository<Deportista, Integer> {
   List<Deportista> findByNombre(String nombre);
}
