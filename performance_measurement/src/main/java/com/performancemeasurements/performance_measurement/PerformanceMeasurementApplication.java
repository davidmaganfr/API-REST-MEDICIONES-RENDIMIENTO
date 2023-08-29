package com.performancemeasurements.performance_measurement;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.performancemeasurements.performance_measurement.DAO.AntropometriaRepository;
import com.performancemeasurements.performance_measurement.DAO.DeportistaRepository;
import com.performancemeasurements.performance_measurement.DAO.EntrenamientoRepository;
import com.performancemeasurements.performance_measurement.entities.Antropometria;
import com.performancemeasurements.performance_measurement.entities.Deportista;
import com.performancemeasurements.performance_measurement.entities.Entrenamiento;
import com.performancemeasurements.performance_measurement.entities.Deportista.Deporte;

@SpringBootApplication
public class PerformanceMeasurementApplication {

	public static void main(String[] args) {
		var context= SpringApplication.run(PerformanceMeasurementApplication.class, args);
		var repoEnnto =context.getBean(EntrenamientoRepository.class);
		var repoDep = context.getBean(DeportistaRepository.class);
		var repoAntro = context.getBean(AntropometriaRepository.class);

		//DEPORTISTAS
		var deportista1 = Deportista.of("David", "Magan", Deporte.CICLISMO);
		var deportista2 = Deportista.of("Jesus", "Lopez", Deporte.CICLISMO);
		var deportista3 = Deportista.of("Javier", "Gomez", Deporte.ATLETISMO);

		//ENTRENAMIENTOS
		var entrenamiento1 = Entrenamiento.of(LocalDate.now(), LocalTime.of(2,56,32), 220, 250);
		var entrenamiento2 = Entrenamiento.of(LocalDate.of(2022, 12, 10), LocalTime.of(1,32,32), 200, 290);
		var entrenamiento3 = Entrenamiento.of(LocalDate.of(2022, 10, 3), LocalTime.of(4,30,30), 200, 235);

		//ANTROPOMETRIAS
		var antropometria1 = new Antropometria(70.0, 172.2, 4.3, 5.4, 10.4, 12.5, 15.6, 8.7, 7.8, 35.9, 32.3, 40.4, 6.2, 4.2, 6.3);
		var antropometria2 = new Antropometria(75.0, 172.2, 4.3, 5.4, 10.4, 12.5, 15.6, 8.7, 7.8, 35.9, 32.3, 40.4, 6.2, 4.2, 6.3);
		var antropometria3 = new Antropometria(80.0, 172.2, 4.3, 5.4, 10.4, 12.5, 15.6, 8.7, 7.8, 35.9, 32.3, 40.4, 6.2, 4.2, 6.3);

		//Añadir entrenamientos y antropometrias al deportista1
		deportista1.addEntrenamiento(entrenamiento1);
		deportista1.addEntrenamiento(entrenamiento2);
		deportista1.addEntrenamiento(entrenamiento3);
		deportista1.addAntropometria(antropometria1);
		deportista1.addAntropometria(antropometria2);
		deportista1.addAntropometria(antropometria3);

		//
		repoDep.save(deportista1);
		repoAntro.save(antropometria1);
	}

}
