package com.sv.serfinsa.service;

import java.util.Optional;

import com.sv.serfinsa.entity.Usuario;

public interface ServiceUsuario {
	
	public Optional<Usuario> obtenerNombre(String nombreUsuario);
	public boolean existsByNombreUsuario(String nombreUsuario);
	public boolean existsByNombreEmail(String email);
	public Usuario existByEmailId(String email) ;
	public Usuario extraerNombreUsuario(String nombre);
	public boolean verificarUserId(Integer id);
	public Usuario extraerUsuarioId(Integer id);
	public void save(Usuario user);
}
