package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domair.model.OrdemServico;
@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

}
