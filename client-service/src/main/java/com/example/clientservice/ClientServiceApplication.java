package com.example.clientservice;

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
public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping("/")
	public String home() {
		return "Hello from Client Service";
	}

	@GetMapping("/clients")
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
}

@Entity
class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private Float age;

	public Client() {}

	public Client(String nom, Float age) {
		this.nom = nom;
		this.age = age;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	public Float getAge() { return age; }
	public void setAge(Float age) { this.age = age; }
}

interface ClientRepository extends JpaRepository<Client, Long> {
}
