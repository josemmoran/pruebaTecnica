package com.sv.serfinsa.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.serfinsa.dto.generic.ErrorResponse;
import com.sv.serfinsa.dto.generic.Metadata;
import com.sv.serfinsa.dto.generic.Request;
import com.sv.serfinsa.entity.Producto;
import com.sv.serfinsa.service.ServiceProducto;
import com.sv.serfinsa.util.CreateResponse;
import com.sv.serfinsa.validation.ValidationRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping(name = "/product")
public class ControllerProduct {
	
	@Autowired
	private ServiceProducto service;

	@GetMapping(name = "/listProduto",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listProduct(){
		return CreateResponse.returnOk(new Metadata(null, null, "GetAll", UUID.randomUUID().toString()),service.findAll());
	}
	
	
	@PostMapping(name = "/addProduct",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addProduct(@Valid @RequestBody Request<Producto> request,BindingResult validation){
	
        if (validation.hasErrors()) {
        	List<ErrorResponse> error = ValidationRequest.validation(request);
            return CreateResponse.returnBadRequest(request.getMetadata(), error);
        }
	
        ErrorResponse response = service.insertProduct(request.getData().getBody());
        
        if(response.getCode().equalsIgnoreCase("000")){
        	  return CreateResponse.returnOk(request.getMetadata(),response);
        }
        else{
        	return CreateResponse.returnResponseError(request.getMetadata(), response);	
        }
	}
	
	@PostMapping(path = "/updateProduct/{idProduct}")
    public ResponseEntity<Object> deleteProduct(@Valid @RequestBody Request<Producto> request, @PathVariable("idProduct") Integer idProducto,BindingResult validation){
    	
    	if (validation.hasErrors()) {
    		List<ErrorResponse> error = ValidationRequest.validation(request);
            return CreateResponse.returnBadRequest(request.getMetadata(), error);
		}
    	
    	ErrorResponse response = service.updateProduct(request.getData().getBody(), idProducto);
    	
    	if(response.getCode().equalsIgnoreCase("000")){
      	  return CreateResponse.returnOk(request.getMetadata(),response);
      }
      else{
      	return CreateResponse.returnResponseError(request.getMetadata(), response);	
      }
    }
	
	
	
	
	
}
