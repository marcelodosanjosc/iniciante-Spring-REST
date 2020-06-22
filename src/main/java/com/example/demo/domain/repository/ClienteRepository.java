package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domair.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	List<Cliente> findByNome(String nom);
	List<Cliente> findByNomeContaining(String nome);
	Cliente findByEmail(String email);
}
