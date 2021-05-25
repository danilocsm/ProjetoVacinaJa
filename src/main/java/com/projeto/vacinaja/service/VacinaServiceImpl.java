package com.projeto.vacinaja.service;

import com.projeto.vacinaja.model.vacina.Vacina;
import com.projeto.vacinaja.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacinaServiceImpl implements VacinaService{

    @Autowired
    private VacinaRepository vacinaRepository;

    @Override
    public void salvarVacina(Vacina vacina) {
        this.vacinaRepository.save(vacina);
    }

    @Override
    public void removerVacina(String idVacina) {
        Vacina aux = this.vacinaRepository.getOne(idVacina);
        this.vacinaRepository.delete(aux);
    }

    @Override
    public List<Vacina> listarVacinas() {
        return this.vacinaRepository.findAll();
    }

    @Override
    public Optional<Vacina> consultarVacinaPorId(String idVacina) {
        return this.vacinaRepository.findById(idVacina);
    }
}
