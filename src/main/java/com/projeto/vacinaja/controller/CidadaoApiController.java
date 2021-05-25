package com.projeto.vacinaja.controller;

import com.projeto.vacinaja.model.Agendamento;
import com.projeto.vacinaja.model.estado.*;
import com.projeto.vacinaja.model.usuario.Role;
import com.projeto.vacinaja.model.usuario.Usuario;
import com.projeto.vacinaja.model.vacina.LoteVacina;
import com.projeto.vacinaja.model.vacina.Vacina;
import com.projeto.vacinaja.repository.RoleRepository;
import com.projeto.vacinaja.service.AgendamentoService;
import com.projeto.vacinaja.service.LoteVacinaService;
import com.projeto.vacinaja.service.UsuarioService;
import com.projeto.vacinaja.service.VacinaService;
import com.projeto.vacinaja.util.ErroAgendamento;
import com.projeto.vacinaja.util.ErroCidadao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CidadaoApiController {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    UsuarioService usuarioService;

    @Autowired
    AgendamentoService agendamentoService;

    @Autowired
    LoteVacinaService loteVacinaService;

    @Autowired
    VacinaService vacinaService;
    
    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/cadastro", method=RequestMethod.POST)
    public ResponseEntity<?> cadastrarDadosCidadao(String cpf, String nome, String endereco, String email, String telefone, String dataDeNascimento,
                    int idade, String numeroSUS, String profissao, String userName, String password) {
        Optional<Usuario> optionalCidadao = usuarioService.retornaUsuarioPeloCpf(cpf);
        if(optionalCidadao.isPresent()) {
            return ErroCidadao.erroCidadaoJaCadastrado();
        }
        Collection<Role> cidadaoRole = new HashSet<>();
        cidadaoRole.add(roleRepository.findByName("CIDADAO"));
        
        Usuario novoCidadao = new Usuario(cpf, nome, endereco, email, dataDeNascimento, telefone, dataDeNascimento, idade, numeroSUS, profissao, null, null, userName, passwordEncoder.encode(password), cidadaoRole);
        usuarioService.cadastrarUsuario(novoCidadao);
        return new ResponseEntity<String>("Cidadão cadastrado", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cidadao/{cpf}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarCadastro(@PathVariable("cpf")String cpf, String nomeCompleto, String endereco, String email, String telefone, String profissao) {
        Optional<Usuario> optionalCidadao = usuarioService.retornaUsuarioPeloCpf(cpf);
         if(!optionalCidadao.isPresent()) {
            return ErroCidadao.erroCidadaoNaoEncontrado();
        }
        Usuario tempCidadao = optionalCidadao.get();
        tempCidadao.setNomeCompleto(nomeCompleto);
        tempCidadao.setEndereco(endereco);
        tempCidadao.setEmail(email);
        tempCidadao.setTelefone(telefone);
        tempCidadao.setProfissao(profissao);
        
        usuarioService.cadastrarUsuario(tempCidadao);
       return new ResponseEntity<String>("Dados atualizados com sucesso", HttpStatus.OK); 
    }
	
    @RequestMapping(value = "/cidadao/{cpf}/estadovacinacao", method= RequestMethod.GET)
    public ResponseEntity<?> checarEstadoVacinacao(@PathVariable("cpf")String cpf) {
        Optional<Usuario> optionalCidadao = usuarioService.retornaUsuarioPeloCpf(cpf);
        if(!optionalCidadao.isPresent()) {
            return ErroCidadao.erroCidadaoNaoEncontrado();
        }
        return new ResponseEntity<String>(optionalCidadao.get().getEstadoVacinacao().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cidadao/{cpf}/agendamento", method = RequestMethod.PUT)
    public ResponseEntity<?> agendarVacinacao(@PathVariable("cpf")String cpf) {
        Optional<Usuario> optionalCidadao = usuarioService.retornaUsuarioPeloCpf(cpf);
        if(!optionalCidadao.isPresent()) {
            return ErroCidadao.erroCidadaoNaoEncontrado();
        }
        Usuario currentCidadao = optionalCidadao.get();
        Optional<Vacina> optionalVacina = vacinaService.consultarVacinaPorId(currentCidadao.getCarteiraVacinacao().getNomeVacina());
        Optional<LoteVacina> optionalLote = loteVacinaService.consultarLotePorVacina(optionalVacina.get());
        boolean habilitado = (currentCidadao.getEstadoVacinacao() == EstadoVacinacao.HABILITADO_SEGUNDA_DOSE) || (currentCidadao.getEstadoVacinacao() == EstadoVacinacao.HABILITADO_PRIMEIRA_DOSE);
        int dose = (currentCidadao.getEstadoVacinacao() == EstadoVacinacao.HABILITADO_PRIMEIRA_DOSE ? 1 : 2);
        if(optionalLote.get().getDoses() >= 0 && habilitado) {
            agendamentoService.cadastrarAgendamento(new Agendamento(cpf, dose, "05/05/2022-14:00"));
        }
        return new ResponseEntity<String>("Agendamento realizado com sucesso", HttpStatus.OK);
    }

    @RequestMapping(value = "/cidado/{cpf}/agendamento", method = RequestMethod.GET)
    public ResponseEntity<?> checarAgendamento(@PathVariable("cpf")String cpf) {
        Optional<Usuario> cidadao = usuarioService.retornaUsuarioPeloCpf(cpf);
        if(!cidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado();
        }
        Optional<Agendamento> optionalAgendamento = agendamentoService.recuperarAgendamentoPorCpf(cpf);
        if(!optionalAgendamento.isPresent()){
            return ErroAgendamento.agendamentoNaoRealizado();
        }
        return new ResponseEntity<Agendamento>(optionalAgendamento.get(), HttpStatus.OK);
    }
}
