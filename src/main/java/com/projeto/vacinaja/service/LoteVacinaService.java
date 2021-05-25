package com.projeto.vacinaja.service;

import com.projeto.vacinaja.model.vacina.LoteVacina;
import com.projeto.vacinaja.model.vacina.Vacina;

import java.util.List;
import java.util.Optional;

public interface LoteVacinaService {

    public void salvarLoteVacina(LoteVacina loteVacina);
    public void removerLoteVacina(long idLoteVacina);
    public Optional <LoteVacina> consultarLotePorVacina(Vacina vacina);
    public List <LoteVacina> listarLotesVacinas();
    public Optional <LoteVacina> consultarLotePorId(long id);
    public int numeroTotalDoses();

}
