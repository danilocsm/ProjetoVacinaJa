package com.projeto.vacinaja.controller;

import com.projeto.vacinaja.model.vacina.LoteVacina;
import com.projeto.vacinaja.model.vacina.Vacina;
import com.projeto.vacinaja.service.LoteVacinaService;
import com.projeto.vacinaja.service.VacinaService;
import com.projeto.vacinaja.util.ErroLoteVacina;
import com.projeto.vacinaja.util.ErroVacina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoteVacinaApiController {

	@Autowired
	VacinaService vacinaService;
	@Autowired
	LoteVacinaService loteVacinaService;

	@RequestMapping(value = "/vacina/lotes", method = RequestMethod.GET)
	public ResponseEntity<?> listarLotes() {
		List<LoteVacina> lotes = loteVacinaService.listarLotesVacinas();
		if (lotes.isEmpty()){
			return ErroLoteVacina.erroNenhumLoteCadastrado();
		}
		return new ResponseEntity<List<LoteVacina>>(lotes, HttpStatus.OK);
	}

	@RequestMapping(value = "/vacina/{id}/lote", method = RequestMethod.POST)
	public ResponseEntity<?> cadastraLote(@PathVariable("id") String id, @RequestBody int numeroDoses, String dataDeValidade) {
		Optional<Vacina> optionalVacina = vacinaService.consultarVacinaPorId(id);
		if (!optionalVacina.isPresent()){
			return ErroVacina.erroVacinaNaoEncontrada(id);
		}
		if(numeroDoses > 2 && numeroDoses > 0){
			return ErroVacina.erroVacinaNumeroDeDoseInvalido();
		}
		LoteVacina novoLote = new LoteVacina(optionalVacina.get(), numeroDoses, dataDeValidade);
		loteVacinaService.salvarLoteVacina(novoLote);
		return new ResponseEntity<LoteVacina>(novoLote, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/vacina/{id}/lote", method = RequestMethod.GET)
	public ResponseEntity<?> consultarLotePorVacina(@PathVariable String id) {
		Optional<Vacina> optionalVacina = vacinaService.consultarVacinaPorId(id);
		if (!optionalVacina.isPresent()){
			return ErroVacina.erroVacinaNaoEncontrada(id);
		}
		Optional<LoteVacina> optionalLote = loteVacinaService.consultarLotePorVacina(optionalVacina.get());
		return new ResponseEntity<LoteVacina>(optionalLote.get(), HttpStatus.OK);
	}
}
