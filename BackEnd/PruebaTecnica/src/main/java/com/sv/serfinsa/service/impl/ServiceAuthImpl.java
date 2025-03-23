package com.sv.serfinsa.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sv.serfinsa.dto.DtoLogin;
import com.sv.serfinsa.dto.DtoUsuario;
import com.sv.serfinsa.dto.ErrorResponse;
import com.sv.serfinsa.dto.JwtDto;
import com.sv.serfinsa.entity.Rol;
import com.sv.serfinsa.entity.Usuario;
import com.sv.serfinsa.security.jwt.JwtService;
import com.sv.serfinsa.service.ServiceAuth;
import com.sv.serfinsa.service.ServiceRol;
import com.sv.serfinsa.service.ServiceUsuario;
import com.sv.serfinsa.util.RolNombre;

@Service
public class ServiceAuthImpl implements ServiceAuth{

	private static final Logger LOG = LoggerFactory.getLogger(ServiceAuthImpl.class);
	
	@Autowired
	private ServiceUsuario serviceUser;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ServiceRol serviceRol;
	
	@Autowired
	private AuthenticationManager authenticationMaganer;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public Object getValidateLogin(DtoLogin body) {
		try {
			Authentication authentication = authenticationMaganer.authenticate(new UsernamePasswordAuthenticationToken(body.getNombreUsuario(), body.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String StringJwt = jwtService.getToken(authentication);
			if(StringJwt==null) {
				return new ErrorResponse("022", "Error en el proceso", "Validacion de credenciales");
			}
			JwtDto jwt = new JwtDto(StringJwt);
			
			Usuario user = serviceUser.extraerNombreUsuario(body.getNombreUsuario());
			if(user.getEstado().equals("B")) {
				return new ErrorResponse("023", "Usuario Bloqueado", "El usuario ha sido bloqueado");
			}
			else {
				LOG.info("Actualizando intentos a cero");
				user.setIntentosIngreso(0);
				serviceUser.save(user);
				LOG.info("Actualizacion OK...");
				return jwt;
			}
		} 
		catch (Exception e) {
LOG.info("Actualizando intentos si el usuario existe");
			
			if(body.getNombreUsuario()!= null || body.equals("")) {
				boolean validar = serviceUser.existsByNombreUsuario(body.getNombreUsuario());
			if(validar) {
					LOG.info("Usuario existe");
					Usuario user = serviceUser.extraerNombreUsuario(body.getNombreUsuario());
					LOG.info("Usuario: "+user.toString());
					LOG.info("Actualizando intentos del usuario "+user.getNombreUsuario());
					LOG.info("Intento realizados ingresar "+user.getIntentosIngreso());
					Integer numeroIngresos = user.getIntentosIngreso();
					Integer numeroNuevo = numeroIngresos+1;
					LOG.info("numero de insgresos nuevo "+numeroNuevo);
					if(numeroNuevo>=3) {
						
						user.setIntentosIngreso(numeroNuevo);
						LOG.info("Usuario bloqueado");
						user.setEstado("B");
						LOG.info("Actualizando..");
						serviceUser.save(user);
						LOG.info("Actualizacion OK...");
						return new ErrorResponse("024","Usuario bloqueado","Los intentos necesarios se han accedido ");
					}
					else {
						LOG.info("contado intentos "+ numeroNuevo);
						user.setIntentosIngreso(numeroNuevo);
						LOG.info("Actualizando..");
						serviceUser.save(user);
						LOG.info("Actualizacion OK...");
					}
				}
			}
			else {
				LOG.info("Usuario no existe ");
			}
			LOG.info("Error al autenticar "+e.getMessage(), e) ;
			return new ErrorResponse("025","El usuario o contraseña invalido","El usuario o contraseña no se encuentran en el sistema");
		}
		
		
	}

	@Override
	public ErrorResponse createUsuario(DtoUsuario body) {
		
		if(serviceUser.existsByNombreUsuario(body.getNombreUsuario())) {
			return new ErrorResponse("020", "Usuario Duplicado","El nombre del usuario ingresado ya existe");
		}
		else if(serviceUser.existsByNombreEmail(body.getEmail())) {
			return new ErrorResponse("021", "Usuario Duplicado","El nombre del usuario ingresado ya existe");
		}
		LOG.info("Usuario OK...");
		
		LOG.info("Validando si el correo existe");
		Usuario user = new Usuario();
		user.setNombre(body.getNombre());
		user.setNombreUsuario(body.getNombreUsuario());
		user.setEmail(body.getEmail());
		user.setPassword(passwordEncoder.encode(body.getPassword()));
		Set<Rol> roles = new HashSet<>();
		
		LOG.info("Asignando roles al usuario");
		roles.add(serviceRol.getRolNombre(RolNombre.ROLE_USER).get());
		if(body.getRoles().contains("admin")) {
			roles.add(serviceRol.getRolNombre(RolNombre.ROLE_ADMIN).get());
		}
		user.setRoles(roles);
		
		LOG.info("Guardando usuario");
		try {
			serviceUser.save(user);
			return new ErrorResponse("000", "Usuario guardado correctamente", "Usuario fue guardado exitosamente");
		} catch (Exception e) {
			LOG.info("{}",e);
			return new ErrorResponse("011", "Error generico", "Error al realizar accion");
		}
		
	}

}
