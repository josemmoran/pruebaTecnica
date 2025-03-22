package com.sv.serfinsa.service;

import java.util.List;

import com.sv.serfinsa.dto.generic.ErrorResponse;
import com.sv.serfinsa.entity.Producto;

public interface ServiceProducto {

	public List<?> findAll();
	public ErrorResponse insertProduct(Producto product);	
	public ErrorResponse updateProduct(Producto product,Integer id);
	public ErrorResponse deleteProduct(Integer id);
	public Object findById(Integer id);
}
