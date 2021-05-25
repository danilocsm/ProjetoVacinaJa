package com.projeto.vacinaja.repository;

import com.projeto.vacinaja.model.vacina.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacina, String> {
}
