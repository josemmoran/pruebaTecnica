package com.sv.serfinsa.service;

import java.util.Optional;

import com.sv.serfinsa.entity.Rol;
import com.sv.serfinsa.util.RolNombre;

public interface ServiceRol {

	public Optional<Rol> findByRolNombre(RolNombre rolNombre);
	public Optional<Rol> getRolNombre(RolNombre rolNombre);
}
