package com.sv.serfinsa.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sv.serfinsa.entity.Rol;
import com.sv.serfinsa.repository.RolRepository;
import com.sv.serfinsa.service.ServiceRol;
import com.sv.serfinsa.util.RolNombre;

@Service
public class ServiceRolImpl implements ServiceRol{

	@Autowired
	private RolRepository adminRol;

	public Optional<Rol> findByRolNombre(RolNombre rolNombre){
		return adminRol.findByRolNombre(rolNombre);
	}
	
	public Optional<Rol> getRolNombre(RolNombre rolNombre){
		return adminRol.findByRolNombre(rolNombre);
	}
	
}
