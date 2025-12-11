package com.example.voitureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@SpringBootApplication
@RestController
public class VoitureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoitureServiceApplication.class, args);
	}

	@Autowired
	private VoitureRepository voitureRepository;

	@GetMapping("/")
	public String home() {
		return "Hello from Voiture Service";
	}

	@GetMapping("/voitures")
	public List<Voiture> getAllVoitures() {
		return voitureRepository.findAll();
	}
}

@Entity
class Voiture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marque;
	private String matricule;
	private String model;
	private Long id_client;

	public Voiture() {}

	public Voiture(String marque, String matricule, String model, Long id_client) {
		this.marque = marque;
		this.matricule = matricule;
		this.model = model;
		this.id_client = id_client;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getMarque() { return marque; }
	public void setMarque(String marque) { this.marque = marque; }
	public String getMatricule() { return matricule; }
	public void setMatricule(String matricule) { this.matricule = matricule; }
	public String getModel() { return model; }
	public void setModel(String model) { this.model = model; }
	public Long getId_client() { return id_client; }
	public void setId_client(Long id_client) { this.id_client = id_client; }
}

interface VoitureRepository extends JpaRepository<Voiture, Long> {
}
