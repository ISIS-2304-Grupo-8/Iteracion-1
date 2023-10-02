package uniandes.edu.co.proyecto;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.proyecto.modelo.TipoHabitacionEntity;
import uniandes.edu.co.proyecto.repositories.TipoHabitacionRepository;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

	@Autowired
	private TipoHabitacionRepository tipoHabitacionRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	// @Override
	// public void run(String... arg) {
	// 	Collection<PlanDeConsumoEntity> planes = planDeConsumoRepository.darPlanesDeConsumo();
	// 	for (PlanDeConsumoEntity plan : planes) {
	// 		System.out.println(plan);
	// 	}
	// }
	@Override
	public void run(String... arg) {
		Collection<TipoHabitacionEntity> tipos_de_habitaciones = tipoHabitacionRepository.darTiposDeHabitaciones();
		for (TipoHabitacionEntity tipo : tipos_de_habitaciones) {
			System.out.println(tipo);
		}
	}

}
