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

import com.performancemeasurements.performance_measurement.DAO.EntrenamientoRepository;
import com.performancemeasurements.performance_measurement.entities.Entrenamiento;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/entrenamiento")
public class EntrenamientoController {
    
    @Autowired
    private EntrenamientoRepository repo;

    @GetMapping("/all")
    public Flux<Entrenamiento> findAll(){
        return Flux.fromIterable(repo.findAll());
    }

    @GetMapping("/find/id/{id:\\d+}")
    public Mono<Entrenamiento> findById(@PathVariable int id){
        var ant = repo.findById(id);
        if(ant.isEmpty()){
            throw new RuntimeException("No existe el entrenamiento con id: " + id);
        }
        return Mono.just(ant.get());
    }

    @GetMapping("/find/date/{fecha:\\\\d{4}-\\\\d{1,2}-\\\\d{1,2}}")
    public Flux<Entrenamiento> findByDate(@PathVariable String fecha){
        var fechaToLocalDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        var entto = repo.findByFecha(fechaToLocalDate);
        if(entto.isEmpty()){
            throw new RuntimeException("No existen registros para la fecha: " + fecha);
        }
        return Flux.fromIterable(entto);
    }

    @PostMapping("/create")
    public Mono<Entrenamiento> create(@RequestBody Entrenamiento entto){
        repo.save(entto);
        return Mono.just(entto);
    }

    @PutMapping("/update/{id:\\d+}")
    public void update(@RequestBody Entrenamiento entto, @PathVariable int id){
        entto.setId(id);
        if(!repo.existsById(id)){
            throw new RuntimeException("No existe el registro con id: " + id);
        }
        repo.save(entto);
    }

    @DeleteMapping("/delete/{id:\\d+}")
    public void delete(@PathVariable int id){
        if(!repo.existsById(id)){
            throw new RuntimeException("No existe el registro con id: " + id);
        } 
        repo.deleteById(id);
    }
}
