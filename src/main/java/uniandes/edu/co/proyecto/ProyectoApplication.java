package uniandes.edu.co.proyecto;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.proyecto.modelo.DescuentoEntity;
import uniandes.edu.co.proyecto.modelo.Estado_reserva;
import uniandes.edu.co.proyecto.modelo.PlanDeConsumoEntity;
import uniandes.edu.co.proyecto.modelo.TipoHabitacionEntity;
import uniandes.edu.co.proyecto.repositories.DescuentoRepository;
import uniandes.edu.co.proyecto.repositories.Estado_reservaRepository;
import uniandes.edu.co.proyecto.repositories.PlanDeConsumoRepository;
import uniandes.edu.co.proyecto.repositories.TipoHabitacionRepository;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

	@Autowired
	private PlanDeConsumoRepository planDeConsumoRepository;

	@Autowired
	private Estado_reservaRepository estado_reservaRepository;

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
		Collection<Estado_reserva> planes = estado_reservaRepository.darEstados_reserva();
		for (Estado_reserva plan : planes) {
			System.out.println(plan);
		}
	}

}
