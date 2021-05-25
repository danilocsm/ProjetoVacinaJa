package com.projeto.vacinaja.controller;

import com.projeto.vacinaja.model.PerfilVacinacao;

import com.projeto.vacinaja.model.usuario.Usuario;
import com.projeto.vacinaja.model.vacina.LoteVacina;
import com.projeto.vacinaja.model.vacina.Vacina;
import com.projeto.vacinaja.service.LoteVacinaService;
import com.projeto.vacinaja.service.UsuarioService;
import com.projeto.vacinaja.service.VacinaService;
import com.projeto.vacinaja.util.ErroCidadao;
import com.projeto.vacinaja.util.ErroFuncionario;
import com.projeto.vacinaja.util.ErroLoteVacina;
import com.projeto.vacinaja.util.ErroVacina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FuncionarioApiController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	VacinaService vacinaService;
	
	@Autowired
	LoteVacinaService loteService;

	@RequestMapping(value = "/funcionario/listar", method = RequestMethod.GET)
	public ResponseEntity<?> listarFuncionarios() {
		List<Usuario> retorno = new ArrayList<>();
		for(Usuario usuario : usuarioService.listarUsuarios()) {
			if(usuario.isFuncionario())
				retorno.add(usuario);
		}
		if(retorno.isEmpty())
			return ErroFuncionario.erroNenhumFuncionarioCadastrado();
		
		return new ResponseEntity<List<Usuario>>(retorno, HttpStatus.OK);
	}

	@RequestMapping(value = "/registroFuncionario/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarFuncionario(@PathVariable ("id") String cpf, String cargo, String localTrabalho) {
		Optional<Usuario> optionalCidadao = usuarioService.retornaUsuarioPeloCpf(cpf);
		if (!optionalCidadao.isPresent()) {
			return ErroCidadao.erroCidadaoNaoEncontrado();
		}
		
		optionalCidadao.get().setCargo(cargo);
		optionalCidadao.get().setLocalTrabalho(localTrabalho);
		
		usuarioService.cadastrarUsuario(optionalCidadao.get());
		return new ResponseEntity<String>("Funcionário Cadastradado", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarFuncionario(@PathVariable("id") String cpf) {
		Optional<Usuario> optionalFuncionario = usuarioService.retornaUsuarioPeloCpf(cpf);
		if (optionalFuncionario.isPresent() && optionalFuncionario.get().isFuncionario()) {
			return new ResponseEntity<Usuario>(optionalFuncionario.get(), HttpStatus.OK);
		}
		return ErroFuncionario.erroFuncionarioNaoEncontrado();
	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerFuncionario(@PathVariable("id") String cpf) {
		Optional<Usuario> optionalFuncionario = usuarioService.retornaUsuarioPeloCpf(cpf);
		if (optionalFuncionario.isPresent() && optionalFuncionario.get().isFuncionario()) {
			usuarioService.removerUsuario(cpf);
			return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
		}
		return ErroFuncionario.erroFuncionarioNaoEncontrado();
	}

	@RequestMapping(value = "/funcionario/habilitarCidadao", method = RequestMethod.PUT)
	public ResponseEntity<?> habilitarCidadaoParaVacinacao(@RequestBody PerfilVacinacao perfil) {
		int dosesDisponiveis = loteService.numeroTotalDoses();
		usuarioService.habilitarCidadaoParaVacinacao(dosesDisponiveis, perfil);
		return new ResponseEntity<String>("Cidadão habilitado para a 1ª dose.", HttpStatus.OK);
	}

	@RequestMapping(value = "/funcionario/registrarVacina/carteira", method = RequestMethod.PUT)
	public ResponseEntity<?> registrarVacinacaoDeCidadao(@RequestBody String cpf, long loteVacina,String nomeVacina, int numeroDose) {

		Optional<Vacina> optionalVacina = vacinaService.consultarVacinaPorId(nomeVacina);
		Optional<Usuario> optionalCidadao = usuarioService.retornaUsuarioPeloCpf(cpf);
		Optional<LoteVacina> optionalLote = loteService.consultarLotePorId(loteVacina);
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAtual = new Date(System.currentTimeMillis());
		long diff = 0;
		try {
			Date validadeLote = data.parse(optionalLote.get().getDataDeValidade());
			diff = validadeLote.getTime() - dataAtual.getTime();
		}catch (ParseException e) {
			e.printStackTrace();
		}

		if (!optionalVacina.isPresent()) {
			return ErroVacina.erroVacinaNaoEncontrada(nomeVacina);
		}
		if (optionalVacina.get().getNumeroDoses() < numeroDose) {
			return ErroVacina.erroVacinaNaoPossuiDose(nomeVacina);
		}
		if (!optionalCidadao.isPresent()) {
			return ErroCidadao.erroCidadaoNaoEncontrado();
		}
		if(!optionalLote.isPresent()) {
			return ErroLoteVacina.erroLoteVacinaNaoCadastrada(loteVacina);
		}
		if(!optionalLote.get().getVacina().getNomeVacina().equals(nomeVacina)) {
			return ErroLoteVacina.erroLoteVacinaErrada(loteVacina, nomeVacina);
		}
		if(optionalLote.get().getDoses() == 0) {
			return ErroLoteVacina.erroLoteVacinaSemDoses();
		}
		if(diff < 0){
			return ErroLoteVacina.erroLoteVacinaForaDaValidade();
		}

		usuarioService.registrarVacinacaoDeCidadao(cpf, String.valueOf(dataAtual), loteVacina, nomeVacina, numeroDose);
		usuarioService.cadastrarUsuario(optionalCidadao.get()); // Novo. Verificar se está ok.
		return new ResponseEntity<String>("Vacinação registrada com sucesso", HttpStatus.OK);
	}

}
