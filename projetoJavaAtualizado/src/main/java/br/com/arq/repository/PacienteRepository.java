package br.com.arq.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arq.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	// public Users findByEmail(String email);
	Optional<Paciente> findByEmail(String email);
}