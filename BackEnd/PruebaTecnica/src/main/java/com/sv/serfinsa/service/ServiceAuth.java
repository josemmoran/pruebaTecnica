package com.sv.serfinsa.service;

import com.sv.serfinsa.dto.DtoLogin;
import com.sv.serfinsa.dto.DtoUsuario;
import com.sv.serfinsa.dto.ErrorResponse;

public interface ServiceAuth {
	
	public Object getValidateLogin(DtoLogin body);
	
	public ErrorResponse createUsuario(DtoUsuario body); 

}
