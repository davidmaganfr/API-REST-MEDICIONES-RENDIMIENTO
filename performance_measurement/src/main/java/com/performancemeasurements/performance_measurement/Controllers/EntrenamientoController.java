package com.performancemeasurements.performance_measurement.Controllers;

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

    @GetMapping("/find/{id}")
    public Mono<Entrenamiento> findById(@PathVariable int id){
        var ant = repo.findById(id);
        return Mono.just(ant.get());
    }

    @PostMapping("/create")
    public Mono<Entrenamiento> create(@RequestBody Entrenamiento entto){
        repo.save(entto);
        return Mono.just(entto);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Entrenamiento entto, @PathVariable int id){
        entto.setId(id);
        if(repo.existsById(id)){
            throw new RuntimeException("Not found");
        }
        repo.save(entto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        repo.deleteById(id);
    }
}
