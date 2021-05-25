package com.projeto.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroAgendamento {
	static final String AGENDAMENTO_NAO_REALIZADO = "Não existe agendamento realizado para este cpf";
	
	public static ResponseEntity<CustomErrorType> agendamentoNaoRealizado() {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(ErroAgendamento.AGENDAMENTO_NAO_REALIZADO),
				HttpStatus.NOT_FOUND);
	}
}
