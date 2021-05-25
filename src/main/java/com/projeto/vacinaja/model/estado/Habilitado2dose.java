package com.projeto.vacinaja.model.estado;


import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.vacinaja.model.usuario.Usuario;
import com.projeto.vacinaja.service.UsuarioService;

public class Habilitado2dose implements EstadoInterface{

	@Autowired
	UsuarioService usuarioService;
	public void proximoEstado(Usuario cidadao) {
		cidadao.setEstadoVacinacao(EstadoVacinacao.VACINACAO_FINALIZADA);	
		usuarioService.cadastrarUsuario(cidadao);
	}
	
	@Override
	public String toString() {
		return "Habilitado para tomar 2ª dose da vacina";
	}
}
