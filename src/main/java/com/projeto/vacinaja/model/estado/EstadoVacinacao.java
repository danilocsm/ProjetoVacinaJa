package com.projeto.vacinaja.model.estado;

import com.projeto.vacinaja.model.usuario.Usuario;

public enum EstadoVacinacao {
	NAO_HABILITADO(new NaoHabilitado1Dose()), HABILITADO_PRIMEIRA_DOSE(new Habilitado1Dose()),
	ESPERANDO_SEGUNDA_DOSE(new Esperando2dose()), HABILITADO_SEGUNDA_DOSE(new Habilitado2dose()), 
	VACINACAO_FINALIZADA(new FinalizadaVacinacao());

	private EstadoInterface estadoInterface;
	private EstadoVacinacao(EstadoInterface estadoInterface) {
		this.estadoInterface = estadoInterface;
	}
	public void proximoEstado(Usuario usuario) {
		this.estadoInterface.proximoEstado(usuario);
	}
}
