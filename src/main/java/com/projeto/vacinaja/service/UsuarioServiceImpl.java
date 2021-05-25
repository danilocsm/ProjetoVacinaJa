package com.projeto.vacinaja.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.vacinaja.model.PerfilVacinacao;
import com.projeto.vacinaja.model.estado.EstadoVacinacao;
import com.projeto.vacinaja.model.usuario.Role;
import com.projeto.vacinaja.model.usuario.Usuario;
import com.projeto.vacinaja.model.vacina.LoteVacina;
import com.projeto.vacinaja.repository.LoteVacinaRepository;
import com.projeto.vacinaja.repository.RoleRepository;
import com.projeto.vacinaja.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	LoteVacinaRepository loteRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public void cadastrarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public List<Usuario> listarUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> retornaUsuarioPeloCpf(String id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public void removerUsuario(String cpf) {
		Usuario aux = this.usuarioRepository.getOne(cpf);
		this.usuarioRepository.delete(aux);
	}

	@Override
	public EstadoVacinacao consultarEstadoVacinacao(String cpf) {
		Usuario aux = this.usuarioRepository.getOne(cpf);
		return aux.getEstadoVacinacao();
	}

	@Override
	public void habilitarCidadaoParaVacinacao(int dosesDisponiveis, PerfilVacinacao perfil) {

		List<Usuario> cidadaos = this.usuarioRepository.findAll();
		List<String> comorbidades = perfil.getListaDeComorbidades();
		List<String> profissoes = perfil.getProfissaoDePrioridade();

		int cont = 0;
		while (dosesDisponiveis > 0 && cidadaos.size() >= cont) {
			Usuario c = cidadaos.get(cont);
			if (comorbidades.contains(c.getComorbidade()) || profissoes.contains(c.getProfissao())
					|| perfil.getIdadeDePrioridade() <= c.getIdade()) {

				if (c.getEstadoVacinacao() == EstadoVacinacao.NAO_HABILITADO) {
					c.notificarMudancaEstado();
					dosesDisponiveis--;
				}
			}
			cont++;
		}
	}
	
	@Override
	public void registrarVacinacaoDeCidadao(String cpf, String dataVacinacao, long loteVacina, String nomeVacina,
			int numeroDose) {
		Usuario u = usuarioRepository.getOne(cpf);
		LoteVacina lv = loteRepository.getOne(loteVacina);

		if (numeroDose == 1) {
			u.getCarteiraVacinacao().setData1Dose(dataVacinacao);
		} else if (numeroDose == 2) {
			u.getCarteiraVacinacao().setData2Dose(dataVacinacao);
		}
		u.getCarteiraVacinacao().setNumeroDaDose(numeroDose);
		u.getCarteiraVacinacao().setNomeVacina(nomeVacina);
		u.getCarteiraVacinacao().setLoteVacina(loteVacina);
		u.notificarMudancaEstado();
		lv.setDoses(lv.getDoses() - 1);
	}
	
	@Override
	public void aprovaFuncionario(String cpf) {
		Optional<Usuario> funcionario = usuarioRepository.findById(cpf);
		funcionario.get().getRoles().add(roleRepository.findByName("FUNCIONARIO"));
		funcionario.get().setFuncionario(true);
		usuarioRepository.save(funcionario.get());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = usuarioRepository.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("invalid username!");
		}
		return new User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
