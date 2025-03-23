package com.sv.serfinsa.service;

import org.springframework.security.core.userdetails.UserDetails;



public interface ServiceUsuarioDetail {


	public UserDetails loadUserByUsername(String username);
}
