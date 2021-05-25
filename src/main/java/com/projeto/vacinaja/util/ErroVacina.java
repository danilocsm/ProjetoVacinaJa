package com.projeto.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroVacina {

	static final String VACINA_NAO_CADASTRADA = "A vacina %s não existe.";
	static final String NENHUMA_VACINA_CADASTRADA = "Nenhuma vacina cadastrada no sistemas ainda.";
	static final String VACINA_JA_CADASTRADA = "A vacina %s já está cadastrada no sistema.";
	static final String VACINA_NAO_POSSUI_SEGUNDA_DOSE = "A vacina %s não possui essa quantidade de doses.";
	static final String NUMERO_DOSE_INVALIDO = "Número de dose invalido";

	public static ResponseEntity<CustomErrorType> erroVacinaNaoEncontrada(String idVacina) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroVacina.VACINA_NAO_CADASTRADA, idVacina)), HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<CustomErrorType> erroSemVacinasCadastrada() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroVacina.NENHUMA_VACINA_CADASTRADA),
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<CustomErrorType> erroVacinaJaCadastrada(String idVacina) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroVacina.VACINA_JA_CADASTRADA, idVacina)), HttpStatus.CONFLICT);
	}

	public static ResponseEntity<CustomErrorType> erroVacinaNaoPossuiDose(String idVacina) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroVacina.VACINA_NAO_POSSUI_SEGUNDA_DOSE, idVacina)),
				HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<CustomErrorType> erroVacinaNumeroDeDoseInvalido() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroVacina.NUMERO_DOSE_INVALIDO),
				HttpStatus.BAD_REQUEST);
	}
}
