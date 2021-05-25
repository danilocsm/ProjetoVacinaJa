package com.projeto.vacinaja.model;

import java.util.ArrayList;

public class PerfilVacinacao {
    private int idadeVacinacao = 65;
    private ArrayList<String> profissaoVacinacao;
    private ArrayList<String> comorbidadesVacinacao;
      
    public int getIdadeDePrioridade() {
        return this.idadeVacinacao; 
    }
    
    public ArrayList<String> getProfissaoDePrioridade() {
        return this.profissaoVacinacao;
    }
    
    public ArrayList<String> getListaDeComorbidades() {
        return this.comorbidadesVacinacao;
    }
}
