package br.com.arq.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arq.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	// public Users findByEmail(String email);
	Optional<Medico> findByEmail(String email);
}