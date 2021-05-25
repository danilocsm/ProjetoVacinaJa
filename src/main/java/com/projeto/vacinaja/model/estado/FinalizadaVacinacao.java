package com.projeto.vacinaja.model.estado;

import com.projeto.vacinaja.model.usuario.Usuario;

public class FinalizadaVacinacao implements EstadoInterface{

	public void proximoEstado(Usuario cidadao) {
		
	}

	@Override
	public String toString() {
		return "Vacinacao finalizada";
	}
	
	
}
