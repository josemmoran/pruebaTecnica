package com.sv.serfinsa.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sv.serfinsa.entity.Usuario;
import com.sv.serfinsa.repository.UsuarioRepository;
import com.sv.serfinsa.service.ServiceUsuario;

@Service
public class ServiceUsuarioImpl implements ServiceUsuario {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceUsuarioImpl.class); 
	
	@Autowired
	private UsuarioRepository userAdmin;
	
	public Optional<Usuario> obtenerNombre(String nombreUsuario){
		return userAdmin.findByNombreUsuario(nombreUsuario);
	}

	/**
	 * @author Jose Moran
	 * Validando si existe nombre de usuario en la base de datos
	 * retorna true si existe
	 * @param nombreUsuario
	 * @return boolean 
	 * */
	public boolean existsByNombreUsuario(String nombreUsuario)  {
		boolean validar = false;
		Usuario usuario = null;
		try {
			LOG.info("Nombre a verificar "+nombreUsuario);
			usuario = userAdmin.existsByNombreUsuario(nombreUsuario);
			validar = (usuario != null) ? true : false;
			
		} 
		catch (Throwable e) {
			LOG.error("Error "+e.getMessage(),e);
		}
		LOG.info("El usuario existe "+validar);
		return validar;
	}
	
	/**
	 * @author Jose Moran
	 * Validar si el email existe en base de datos
	 * retorna true si existe
	 * @param Email
	 * @return boolean
	 * */
	public boolean existsByNombreEmail(String email) {
		boolean validar;
		Usuario usuario = null;
		try {
			usuario = userAdmin.existsByEmail(email);
			validar = (usuario !=null) ? true : false;
		} catch (Exception e) {
			LOG.error("Error "+e,e);
			validar = false;
		}
		return validar;
	}
	
	/**
	 * @author Jose Moran
	 * Validar si el email existe en base de datos
	 * retorna datos de usuario
	 * @param Email
	 * @return Usuario
	 * */
	public Usuario existByEmailId(String email) {
		Usuario user = null;
		try {
			LOG.info("Email a validar "+ email);
			user = userAdmin.existsByEmail(email);
			LOG.info("Mostrar usuario extraido: "+user.toString());
			return user;
			
		} catch (Throwable e) {
			LOG.error("Error "+e.getMessage(), e);
			return user;
		}
	}
	
	/**
	 * @author Jose Moran
	 * extraer datos de usuario con el nombre completo que agrega
	 * @param nombre
	 * */
	public Usuario extraerNombreUsuario(String nombre) {
		Usuario user = null;
		LOG.info("Nombre a consultar "+nombre);
		user = userAdmin.existsByNombreUsuario(nombre);
		return user;
	}
	
	/**
	 * @author Jose Moran
	 * verifica si el usuario existe por consultando el id que trae en los parametros
	 * @param id
	 * @return boolean
	 * */
	public boolean verificarUserId(Integer id) {
		boolean verificar = false;
		LOG.info("Id a validar "+id);
		verificar = userAdmin.equals(id);
		LOG.info("el usuario existe -> "+verificar);
		return verificar;
	}
	
	/**
	 * @author Jose Moran
	 * extrae el usuario por el id que trae de los parametros
	 * @param id
	 * @return Usuario
	 * */	
	public Usuario extraerUsuarioId(Integer id) {
		Optional<Usuario> user = userAdmin.findById(id);
		return user.get();
	}
	
	/**
	 * @author Jose Moran
	 * Guarda y actualiza el todo el usuario que trae en el parametro
	 * @param Usuario 
	 * */
	public void save(Usuario user) {
		userAdmin.save(user);
	}

}
