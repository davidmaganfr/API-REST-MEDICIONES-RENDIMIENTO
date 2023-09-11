package com.performancemeasurements.performance_measurement.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.performancemeasurements.performance_measurement.DAO.AntropometriaRepository;
import com.performancemeasurements.performance_measurement.entities.Antropometria;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/antropometria")
public class AntropometriaController {
    @Autowired
    private AntropometriaRepository repo;
    
    @GetMapping("/all")
    public Flux<Antropometria> findAll(){
        return Flux.fromIterable(repo.findAll());
    }

    @GetMapping("/find/id/{id:\\d+}")
    public Mono<Antropometria> findById(@PathVariable int id){
        var ant = repo.findById(id);
        if(ant.isEmpty()){
            throw new RuntimeException("No exiten registros con el id: " + id);
        }
        return Mono.just(ant.get());
    }

    @GetMapping("/find/date/{fecha:\\d{4}-\\d{1,2}-\\d{1,2}}")
    public Flux<Antropometria> findByDate(@PathVariable String fecha){
        var fechaToLocalDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        var antro = repo.findByFecha(fechaToLocalDate);
        if (antro.isEmpty()){
            throw new RuntimeException("No existen registros para la fecha: " + fecha);
        }
        return Flux.fromIterable(antro);
    }

    @PostMapping("/create")
    public Mono<Antropometria> create(@RequestBody Antropometria antro){
        repo.save(antro);
        return Mono.just(antro);
    }

    @PutMapping("/update/{id:\\d+}")
    public void update(@RequestBody Antropometria antro, @PathVariable int id){
        antro.setId(id);
        if(!repo.existsById(id)){
            throw new RuntimeException("No existe el registro con id: " + id);
        }
        repo.save(antro);
    }

    @DeleteMapping("/delete/{id:\\d+}")
    public void delete(@PathVariable int id){
        if(!repo.existsById(id)){
            throw new RuntimeException("No existe el registro con id: " + id);
        } 
        repo.deleteById(id);
    }
}
