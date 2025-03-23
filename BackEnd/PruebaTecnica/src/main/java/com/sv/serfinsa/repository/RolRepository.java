package com.sv.serfinsa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.sv.serfinsa.entity.Rol;
import com.sv.serfinsa.util.RolNombre;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {


	@Query("SELECT r FROM Rol r WHERE r.rolNombre = ?1")
	public Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

