package com.projeto.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroFuncionario {
	
	static final String FUNCIONARIO_JA_CADASTRADO = "O funcionário já foi cadastrado";

	static final String NENHUM_FUNCIONARIO_CADASTRADO =  "Até o momento, nenhum funcionário foi cadastrado";

	static final String FUNCIONARIO_NAO_ENCONTRADO =  "O funcionário ainda não foi cadastrado";

	static final String FUNCIONARIO_NAO_APROVADO =  "O funcionário ainda não foi aprovado";

	static final String FUNCIONARIO_JA_APROVADO =  "O funcionário já foi aprovado";

	public static ResponseEntity<CustomErrorType> erroFuncionarioJaCadastrado() {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(ErroFuncionario.FUNCIONARIO_JA_CADASTRADO),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<CustomErrorType> erroNenhumFuncionarioCadastrado() {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(ErroFuncionario.NENHUM_FUNCIONARIO_CADASTRADO),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroFuncionarioNaoEncontrado() {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(ErroFuncionario.FUNCIONARIO_NAO_ENCONTRADO),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroFuncionarioNaoAprovado() {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(ErroFuncionario.FUNCIONARIO_NAO_APROVADO),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroFuncionarioJaAprovado() {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(ErroFuncionario.FUNCIONARIO_JA_APROVADO),
				HttpStatus.NOT_FOUND);
	}


}
