package com.projeto.vacinaja.service;

import com.projeto.vacinaja.model.vacina.LoteVacina;
import com.projeto.vacinaja.model.vacina.Vacina;
import com.projeto.vacinaja.repository.LoteVacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoteVacinaServiceImpl implements LoteVacinaService{

    @Autowired
    private LoteVacinaRepository loteVacinaRepository;

    @Override
    public void salvarLoteVacina(LoteVacina loteVacina) {
        this.loteVacinaRepository.save(loteVacina);
    }

    @Override
    public void removerLoteVacina(long idLoteVacina) {
        LoteVacina aux = this.loteVacinaRepository.getOne(idLoteVacina);
        this.loteVacinaRepository.delete(aux);
    }

    @Override
    public Optional<LoteVacina> consultarLotePorVacina(Vacina vacina) {
        return this.loteVacinaRepository.findByVacina(vacina);
    }

    @Override
    public List<LoteVacina> listarLotesVacinas() {
        return this.loteVacinaRepository.findAll();
    }

	@Override
	public Optional<LoteVacina> consultarLotePorId(long id) {
		return this.loteVacinaRepository.findById(id);
		
	}

    @Override
    public int numeroTotalDoses() {

        int total = 0;
        for(LoteVacina l : this.loteVacinaRepository.findAll()){

            total += l.getDoses();

        }
        return total;
    }
}
