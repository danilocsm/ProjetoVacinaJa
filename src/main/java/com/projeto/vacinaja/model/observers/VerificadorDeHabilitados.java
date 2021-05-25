package com.projeto.vacinaja.model.observers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.projeto.vacinaja.model.estado.EstadoVacinacao;
import com.projeto.vacinaja.model.usuario.Usuario;
import com.projeto.vacinaja.service.UsuarioService;

@Component @EnableScheduling
public class VerificadorDeHabilitados {

	@Autowired
	UsuarioService usuarioService;

	private static final String MEIO_DIA = "0 0 12 30 12 6";
	private static final String TIME_ZONE = "America/Sao_Paulo";
    
    @Scheduled(cron = MEIO_DIA, zone = TIME_ZONE)
    public void notificaHabilitados() {
        List<Usuario> cidadaos = usuarioService.listarUsuarios();
        for(Usuario cidadao: cidadaos) {
        	if(!cidadao.isFuncionario()) {        		
        		if(cidadao.getEstadoVacinacao() == EstadoVacinacao.HABILITADO_PRIMEIRA_DOSE || cidadao.getEstadoVacinacao() == EstadoVacinacao.ESPERANDO_SEGUNDA_DOSE){
        			cidadao.notificarHabilitado();
        		}
        	}
        }
    }
}
