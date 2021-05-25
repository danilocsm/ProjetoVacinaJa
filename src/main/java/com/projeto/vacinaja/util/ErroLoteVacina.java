package com.projeto.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroLoteVacina {

	static final String LOTE_VACINA_NAO_CADASTRADO = "O Lote %s não existe.";
	static final String NENHUM_LOTE_CADASTRADO = "Nenhum lote cadastro.";
	static final String LOTE_JA_CADASTRADO = "Lote %s já cadastrado.";
	static final String LOTE_NAO_EH_DESSA_VACINA = "O lote %s não é da vacina %s.";
	static final String LOTE_SEM_DOSES = "O lote não possui mais doses";
	static final String LOTE_FORA_DA_VALIDADE = "Doses desse lote não estão vencidas.";

	public static ResponseEntity<CustomErrorType> erroLoteVacinaNaoCadastrada(long idLote) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroLoteVacina.LOTE_VACINA_NAO_CADASTRADO, idLote)),
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<CustomErrorType> erroNenhumLoteCadastrado() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLoteVacina.NENHUM_LOTE_CADASTRADO),
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<CustomErrorType> erroLoteJaCadastrado(long idLote) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroLoteVacina.LOTE_JA_CADASTRADO, idLote)), HttpStatus.CONFLICT);
	}

	public static ResponseEntity<CustomErrorType> erroLoteVacinaErrada(long idLote, String idVacina) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroLoteVacina.LOTE_NAO_EH_DESSA_VACINA, idLote, idVacina)),
				HttpStatus.CONFLICT);
	}

	public static ResponseEntity<CustomErrorType> erroLoteVacinaSemDoses() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLoteVacina.LOTE_SEM_DOSES),
				HttpStatus.CONFLICT);
	}
	public static ResponseEntity<CustomErrorType> erroLoteVacinaForaDaValidade() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLoteVacina.LOTE_FORA_DA_VALIDADE),
				HttpStatus.CONFLICT);
	}

}
