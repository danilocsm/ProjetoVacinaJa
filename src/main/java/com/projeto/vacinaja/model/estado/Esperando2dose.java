package com.projeto.vacinaja.model.estado;


import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.vacinaja.model.usuario.Usuario;
import com.projeto.vacinaja.service.UsuarioService;

public class Esperando2dose implements EstadoInterface{

	@Autowired
	UsuarioService usuarioService;
	public void proximoEstado(Usuario cidadao) {
		cidadao.setEstadoVacinacao(EstadoVacinacao.HABILITADO_SEGUNDA_DOSE);
		usuarioService.cadastrarUsuario(cidadao);
	}
	
	@Override
	public String toString() {
		return "1ª dose tomada, esperando para poder tomar 2ª dose";
	}
}
