package com.sv.serfinsa.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sv.serfinsa.entity.Usuario;
import com.sv.serfinsa.entity.UsuarioPrincipal;
import com.sv.serfinsa.service.ServiceUsuario;
import com.sv.serfinsa.service.ServiceUsuarioDetail;

@Service
public class ServiceUsuarioDetailImpl implements ServiceUsuarioDetail {

	@Autowired
	private ServiceUsuario usuarioSer;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = usuarioSer.obtenerNombre(username);
		if(user.isPresent()) {
			Usuario usuario = user.get();
			return UsuarioPrincipal.build(usuario);
		}
		
		return null;
	}

}
