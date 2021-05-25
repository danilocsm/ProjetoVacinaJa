package com.projeto.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCidadao {
	
	static final String CIDADAO_JA_CADASTRADO = "O cidadão já foi cadastrado";

	static final String NENHUM_CIDADAO_CADASTRADO =  "Até o momento, nenhum cidadão foi cadastrado";

	static final String CIDADAO_NAO_ENCONTRADO =  "O cidadão ainda não foi cadastrado";

	public static ResponseEntity<CustomErrorType> erroCidadaoJaCadastrado() {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(ErroCidadao.CIDADAO_JA_CADASTRADO),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<CustomErrorType> erroNenhumCidadaoCadastrado() {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(ErroCidadao.NENHUM_CIDADAO_CADASTRADO),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroCidadaoNaoEncontrado() {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(ErroCidadao.CIDADAO_NAO_ENCONTRADO),
				HttpStatus.NOT_FOUND);
	}
}
