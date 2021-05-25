package com.projeto.vacinaja.model.estado;

import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.vacinaja.model.usuario.Usuario;
import com.projeto.vacinaja.service.UsuarioService;

public class NaoHabilitado1Dose implements EstadoInterface{

	@Autowired
	UsuarioService usuarioService;
	public void proximoEstado(Usuario cidadao) {
		cidadao.setEstadoVacinacao(EstadoVacinacao.HABILITADO_PRIMEIRA_DOSE);
		usuarioService.cadastrarUsuario(cidadao);
	}
	
	@Override
	public String toString() {
		return "Habilitado para tomar primeira dose da vacina";
	}

}
