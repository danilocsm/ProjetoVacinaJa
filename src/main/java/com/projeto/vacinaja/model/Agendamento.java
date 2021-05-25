package com.projeto.vacinaja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue
    long iD;

    private String cpf;

    private int dose;
    
    private String data;
    
    public Agendamento() {}

    public Agendamento(String cpf, int dose, String data) {
        this.cpf = cpf;
        this.data = data;
        this.dose = dose;
    }
    
    public Agendamento(long iD, String cpf, int dose, String data) {
        this.iD = iD;
        this.cpf = cpf;
        this.data = data;
        this.dose = dose;
    }

	public long getiD() {
		return iD;
	}

	public void setiD(long iD) {
		this.iD = iD;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
    
    
}
