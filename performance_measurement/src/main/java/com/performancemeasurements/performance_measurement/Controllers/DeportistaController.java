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

import com.performancemeasurements.performance_measurement.DAO.DeportistaRepository;
import com.performancemeasurements.performance_measurement.entities.Deportista;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/deportista")
public class DeportistaController {

    @Autowired
    private DeportistaRepository repo;

     @GetMapping("/all")
    public Flux<Deportista> findAll(){
        return Flux.fromIterable(repo.findAll());
    }

    @GetMapping("/find/{id}")
    public Mono<Deportista> findById(@PathVariable int id){
        var ant = repo.findById(id);
        return Mono.just(ant.get());
    }

    @GetMapping("/find/{nombre}")
    public Flux<Deportista> findByName(@PathVariable String nombre){
        var antro = repo.findByNombre(nombre);
        if(antro == null){
            throw new RuntimeException("Not found");
        }
        return Flux.fromIterable(antro);
    }

    @PostMapping("/create")
    public Mono<Deportista> create(@RequestBody Deportista deportista){
        repo.save(deportista);
        return Mono.just(deportista);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Deportista deportista, @PathVariable int id){
        deportista.setId(id);
        if(repo.existsById(id)){
            throw new RuntimeException("Not found");
        }
        repo.save(deportista);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        repo.deleteById(id);
    }
}
