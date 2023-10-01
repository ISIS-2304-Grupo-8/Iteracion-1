package uniandes.edu.co.proyecto;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.proyecto.modelo.DescuentoEntity;
import uniandes.edu.co.proyecto.repositories.DescuentoRepository;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

	@Autowired
	private DescuentoRepository descuentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... arg) {
		Collection<DescuentoEntity> descuentos = descuentoRepository.darDescuentos();
		for (DescuentoEntity descuento : descuentos) {
			System.out.println(descuento);
		}
	}

}
