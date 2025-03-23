package com.sv.serfinsa.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DtoUsuario {

	@NotBlank
	@NotNull
	@JsonProperty(value = "name")
	private String nombre;
	
	@NotBlank
	@NotNull
	@JsonProperty(value = "nameUsuario")
	private String nombreUsuario;
	
	@Email
	@NotEmpty
	@NotNull
	@JsonProperty(value = "email")
	private String email;
	
	@NotBlank
	@NotNull
	@JsonProperty(value = "password")
	private String password;
	
	@JsonProperty(value = "rol")
	private Set<String> roles = new HashSet<>();
	
	public DtoUsuario() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
