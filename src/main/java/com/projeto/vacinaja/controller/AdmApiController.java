package com.projeto.vacinaja.controller;

import com.projeto.vacinaja.model.usuario.Usuario;
import com.projeto.vacinaja.service.UsuarioService;
import com.projeto.vacinaja.util.ErroFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AdmApiController {

	@Autowired
	UsuarioService usuarioService;

	@RequestMapping(value = "/admin", method = RequestMethod.PUT)
	public ResponseEntity<?> aprovaCadastroFuncionario(@PathVariable String cpfFuncionario) {
		Optional<Usuario> optionalFuncionario = usuarioService.retornaUsuarioPeloCpf(cpfFuncionario);
		if(!optionalFuncionario.isPresent()) {
			return ErroFuncionario.erroFuncionarioNaoEncontrado();
		}
		usuarioService.aprovaFuncionario(cpfFuncionario);
		return new ResponseEntity<Usuario>((Usuario) null, HttpStatus.ACCEPTED);
		
	}

}
