package com.performancemeasurements.performance_measurement.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "deportista")
public class Deportista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "DEPORTE")
    private Deporte deporte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deportista")
    private List<Antropometria> listaAntropometrias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deportista")
    private List<Entrenamiento> listaEntrenamientos;

    public enum Deporte {
        CICLISMO, ATLETISMO, TRIATLON, FUERZA
    }

    public Deportista(String nombre, String apellido, Deporte deporte) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.deporte = deporte;
    }

    public static Deportista of(String nombre, String apellido, Deporte deporte) {
        return new Deportista(nombre, apellido, deporte);
    }

    public void addAntropometria(Antropometria antro) {
        if (listaAntropometrias == null) {
            listaAntropometrias = new ArrayList<Antropometria>();
        }
        listaAntropometrias.add(antro);
        antro.setDeportista(this);
    }

    public void addEntrenamiento(Entrenamiento entto) {
        if (listaEntrenamientos == null) {
            listaEntrenamientos = new ArrayList<Entrenamiento>();
        }
        listaEntrenamientos.add(entto);
        entto.setDeportista(this);
    }

    // public Antropometria findAntropometria(int dia, int mes, int año) {
    //     var fechaToLocalDate = LocalDate.of(año, mes, dia);
    //     return listaAntropometrias.stream()
    //             .filter(antro -> antro.getFecha().isEqual(fechaToLocalDate))
    //             .findFirst()
    //             .get();
    // }

    // public Entrenamiento findEntrenamiento(int dia, int mes, int año){
    //     var fechaToLocalDate = LocalDate.of(año, mes, dia);
    //     return listaEntrenamientos.stream()
    //             .filter(antro -> antro.getFecha().isEqual(fechaToLocalDate))
    //             .findFirst()
    //             .get();
    // }
}
