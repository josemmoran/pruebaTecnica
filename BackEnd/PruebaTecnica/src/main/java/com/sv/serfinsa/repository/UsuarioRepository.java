package com.sv.serfinsa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sv.serfinsa.entity.Usuario;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1")
	public Optional<Usuario> findByNombreUsuario(String nombreUsuario);

	@Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1")
	public Usuario existsByNombreUsuario(String nombreUsuario);
	
	@Query("SELECT u FROM Usuario u where u.email = ?1")
	public Usuario existsByEmail(String email);

}
