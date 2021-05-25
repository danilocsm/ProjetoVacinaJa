package com.projeto.vacinaja.service;

import java.util.List;
import java.util.Optional;

import com.projeto.vacinaja.model.Agendamento;

public interface AgendamentoService {

    public void cadastrarAgendamento(Agendamento agendamento);
    public Optional<Agendamento> recuperarAgendamentoPorCpf(String cpf);
    public List<Agendamento> listarAgendamentos();
    public void removerAgendamento(Agendamento agendamento);
}
