package com.projeto.vacinaja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.vacinaja.model.Agendamento;
import com.projeto.vacinaja.repository.AgendamentoRepository;

@Service
public class AgendamentoServiceImpl implements AgendamentoService{

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Override
	public void cadastrarAgendamento(Agendamento agendamento) {
		agendamentoRepository.save(agendamento);	
	}

	@Override
	public Optional<Agendamento> recuperarAgendamentoPorCpf(String cpf) {
		return agendamentoRepository.findByCpf(cpf);
	}

	@Override
	public List<Agendamento> listarAgendamentos() {
		return agendamentoRepository.findAll();
	}

	@Override
	public void removerAgendamento(Agendamento agendamento) {
		agendamentoRepository.delete(agendamento);
	}
}
