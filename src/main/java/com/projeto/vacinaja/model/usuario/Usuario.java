package com.projeto.vacinaja.model.usuario;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.projeto.vacinaja.model.estado.EstadoVacinacao;
import com.projeto.vacinaja.model.observers.Notificavel;
import com.projeto.vacinaja.model.vacina.CarteiraVacinacao;
import com.sun.istack.NotNull;

@Entity
public class Usuario implements Notificavel{
	
	@Id
	protected String cpf;
	protected String nomeCompleto;
	protected String endereco;
	protected String email;
	protected String dataNascimento;
	protected String telefone;
	protected String comorbidade;
	protected int idade;
	private String numeroSUS;
	private String profissao;
	private String cargo;
	private String localTrabalho;
	private boolean isFuncionario;
	public String userName;
	public String password;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private EstadoVacinacao estadoVacinacao;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private CarteiraVacinacao carteiraVacinacao;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "cpf"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	public Collection<Role> roles;

	public Usuario() {
	}

	public Usuario(String cpf, String nomeCompleto, String endereco, String email, String dataNascimento,
			String telefone, String comorbidade, int idade, String numeroSUS, String profissao,
			String cargo, String localTrabalho, String userName, String password,
			Collection<Role> roles) {
		super();
		this.cpf = cpf;
		this.nomeCompleto = nomeCompleto;
		this.endereco = endereco;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.comorbidade = comorbidade;
		this.idade = idade;
		this.numeroSUS = numeroSUS;
		this.profissao = profissao;
		this.cargo = cargo;
		this.localTrabalho = localTrabalho;
		this.isFuncionario = false;
		this.estadoVacinacao = EstadoVacinacao.NAO_HABILITADO;
		this.carteiraVacinacao = new CarteiraVacinacao();
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isFuncionario() {
		return isFuncionario;
	}

	public void setFuncionario(boolean isFuncionario) {
		this.isFuncionario = isFuncionario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getComorbidade() {
		return comorbidade;
	}

	public void setComorbidade(String comorbidade) {
		this.comorbidade = comorbidade;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNumeroSUS() {
		return numeroSUS;
	}

	public void setNumeroSUS(String numeroSUS) {
		this.numeroSUS = numeroSUS;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public EstadoVacinacao getEstadoVacinacao() {
		return estadoVacinacao;
	}

	public void setEstadoVacinacao(EstadoVacinacao estadoVacinacao) {
		this.estadoVacinacao = estadoVacinacao;
	}

	public CarteiraVacinacao getCarteiraVacinacao() {
		return carteiraVacinacao;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	@Override
	public void notificarMudancaEstado() {
		this.estadoVacinacao.proximoEstado(this);
	}

	@Override
	public String notificarHabilitado() {
		return "Cidadao está habilitado para vacinação. Ver detalhes no site.";	
	}
}
