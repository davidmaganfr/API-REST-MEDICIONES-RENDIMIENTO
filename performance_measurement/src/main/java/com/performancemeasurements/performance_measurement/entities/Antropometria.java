package com.performancemeasurements.performance_measurement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "antropometria")
public class Antropometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "PESO")
    private double peso;
    @Column(name = "ALTURA")
    private double altura;
    @Column(name = "TRICEPS")
    private double triceps;
    @Column(name = "BICEPS")
    private double biceps;
    @Column(name = "SUBESCAPULAR")
    private double subEscapular;
    @Column(name = "SUPRAESPINAL")
    private double supraEspinal;
    @Column(name = "ABDOMINAL")
    private double abdominal;
    @Column(name = "MULO")
    private double muslo;
    @Column(name = "PIERNA")
    private double pierna;
    @Column(name = "P_BRAZO_CONTR")
    private double perimetroBrazoContraido;
    @Column(name = "P_BRAZO_RELA")
    private double perimetroBrazoRelajado;
    @Column(name = "P_PIERNA")
    private double perimetroPierna;
    @Column(name = "BIESTILOIDEO")
    private double biEstiloideo;
    @Column(name = "HUMERO")
    private double humero;
    @Column(name = "FEMUR")
    private double femur;
    @JsonIgnore
    @JoinColumn(name = "DEPORTISTA_ID")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Deportista deportista;

    public Antropometria(double peso, double altura, double triceps, double biceps, double subEscapular,
            double supraEspinal, double abdominal, double muslo, double pierna, double perimetroBrazoContraido,
            double perimetroBrazoRelajado, double perimetroPierna, double biEstiloideo, double humero, double femur) {
        this.peso = peso;
        this.altura = altura;
        this.triceps = triceps;
        this.biceps = biceps;
        this.subEscapular = subEscapular;
        this.supraEspinal = supraEspinal;
        this.abdominal = abdominal;
        this.muslo = muslo;
        this.pierna = pierna;
        this.perimetroBrazoContraido = perimetroBrazoContraido;
        this.perimetroBrazoRelajado = perimetroBrazoRelajado;
        this.perimetroPierna = perimetroPierna;
        this.biEstiloideo = biEstiloideo;
        this.humero = humero;
        this.femur = femur;
    }
 
}
