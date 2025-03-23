package com.sv.serfinsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.serfinsa.dto.DtoLogin;
import com.sv.serfinsa.dto.DtoUsuario;
import com.sv.serfinsa.dto.ErrorResponse;
import com.sv.serfinsa.dto.JwtDto;
import com.sv.serfinsa.dto.Request;
import com.sv.serfinsa.service.ServiceAuth;
import com.sv.serfinsa.util.CreateResponse;
import com.sv.serfinsa.validation.ValidationRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private ServiceAuth service;
	
	
	@PostMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody Request<DtoLogin> request, BindingResult validation){
		
		if(validation.hasErrors()) {
			List<ErrorResponse> error = ValidationRequest.validation(request);
            return CreateResponse.returnBadRequest(request.getMetadata(), error);
		}
		
		Object obj = service.getValidateLogin(request.getData().getBody());
		
		if(obj instanceof ErrorResponse resp) {
			resp = (ErrorResponse) obj;
			return CreateResponse.returnResponseError(request.getMetadata(), resp);
		}
		else {
			JwtDto jwt = (JwtDto) obj;
			return CreateResponse.returnOk(request.getMetadata(), jwt);
		}
		
	}
	
	@PostMapping(path = "/createUser",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@RequestBody Request<DtoUsuario> request, BindingResult validation){
		
		if(validation.hasErrors()) {
			List<ErrorResponse> error = ValidationRequest.validation(request);
            return CreateResponse.returnBadRequest(request.getMetadata(), error);
		}
		
		ErrorResponse response = service.createUsuario(request.getData().getBody());
		
		if(response.getCode().equalsIgnoreCase("000")) {
			return CreateResponse.returnOk(request.getMetadata(), response);
		}
		else {
			return CreateResponse.returnResponseError(request.getMetadata(), response);
		}
		
	}
	
}
