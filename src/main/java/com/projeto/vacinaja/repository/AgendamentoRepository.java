package com.projeto.vacinaja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.vacinaja.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
    Optional<Agendamento> findByCpf(String cpf);
}
