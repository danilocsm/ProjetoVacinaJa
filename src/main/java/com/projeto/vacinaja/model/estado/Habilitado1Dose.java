package com.projeto.vacinaja.model.estado;


import org.springframework.beans.factory.annotation.Autowired;


import com.projeto.vacinaja.model.usuario.Usuario;
import com.projeto.vacinaja.service.UsuarioService;

public class Habilitado1Dose implements EstadoInterface{
	
	@Autowired
	UsuarioService usuarioService;
	public void proximoEstado(Usuario cidadao) {
		cidadao.setEstadoVacinacao(EstadoVacinacao.ESPERANDO_SEGUNDA_DOSE);
		usuarioService.cadastrarUsuario(cidadao);
	}
	
	@Override
	public String toString() {
		return "apto para tomar primeira dose da vacina";
	}
}
