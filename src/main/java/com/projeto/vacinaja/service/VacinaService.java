package com.projeto.vacinaja.service;

import com.projeto.vacinaja.model.vacina.Vacina;

import java.util.List;
import java.util.Optional;

public interface VacinaService {

    public void salvarVacina(Vacina vacina);
    public void removerVacina(String idVacina);
    public List <Vacina> listarVacinas();
    public Optional<Vacina> consultarVacinaPorId(String idVacina);

}
