package com.example.demo.domain.service;


import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.model.Comentario;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.repository.ClienteRepository;
import com.example.demo.domain.repository.ComentarioRepository;
import com.example.demo.domain.repository.OrdemServicoRepository;
import com.example.demo.domair.model.Cliente;
import com.example.demo.domair.model.OrdemServico;
import com.example.demo.domair.model.StatusOrdemServico;
@Service
public class GestaoOrddemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	@Autowired
	private ClienteRepository clienteRepository; 
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente nao encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		ordemServicoRepository.save(ordemServico);
	}

	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		return comentarioRepository.save(comentario);
	}
	
	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
	}
}
