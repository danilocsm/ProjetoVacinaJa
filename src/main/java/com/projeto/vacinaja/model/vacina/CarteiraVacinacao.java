package com.projeto.vacinaja.model.vacina;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CarteiraVacinacao {
	
	@Id
	@GeneratedValue
	private long iD;
	
	private long loteVacina;
	
    private String data1Dose;
    
    private String data2Dose;

    private int numeroDaDose;
   
    private String nomeVacina;

	public CarteiraVacinacao() {
		
	}

    public CarteiraVacinacao(long iD, String data1Dose, int numeroDaDose, String nomeVacina) {
        this.iD = iD;
    	this.data1Dose = data1Dose;
        this.numeroDaDose = numeroDaDose;
        this.nomeVacina = nomeVacina;
    }

	public String getData1Dose() {
		return data1Dose;
	}

	public void setData1Dose(String data1Dose) {
		this.data1Dose = data1Dose;
	}

	public int getNumeroDaDose() {
		return numeroDaDose;
	}

	public void setNumeroDaDose(int numeroDaDose) {
		this.numeroDaDose = numeroDaDose;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}
	
	public long getId() {
		return this.iD;
	}
	
	public void setData2Dose(String data2Dose) {
		this.data2Dose = data2Dose;
	}
	
	public String getData2Dose() {
		return this.data2Dose;
	}

	public void setLoteVacina(long loteVacina) {
		this.loteVacina = loteVacina;
	}
	
}
