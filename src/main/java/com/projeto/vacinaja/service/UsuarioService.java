package com.projeto.vacinaja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.projeto.vacinaja.model.PerfilVacinacao;
import com.projeto.vacinaja.model.estado.EstadoVacinacao;
import com.projeto.vacinaja.model.usuario.Usuario;

public interface UsuarioService extends UserDetailsService {

	public void cadastrarUsuario(Usuario usuario);

	public List<Usuario> listarUsuarios();
	
	public Optional<Usuario> retornaUsuarioPeloCpf(String cpf);
	
	public void removerUsuario(String cpf);
	
	public EstadoVacinacao consultarEstadoVacinacao(String cpf);
	
	public void habilitarCidadaoParaVacinacao(int dosesDisponiveis, PerfilVacinacao perfil);
	
	public void registrarVacinacaoDeCidadao(String cpf, String dataVacinacao, long loteVacina, String nomeVacina,
			int numeroDose);
	
	public void aprovaFuncionario(String cpf);
}
