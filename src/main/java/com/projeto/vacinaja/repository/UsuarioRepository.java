package com.projeto.vacinaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.vacinaja.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	Usuario findByUserName(String userName);
}
