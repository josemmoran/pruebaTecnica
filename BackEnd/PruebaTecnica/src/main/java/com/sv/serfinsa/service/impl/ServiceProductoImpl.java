package com.sv.serfinsa.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sv.serfinsa.dto.generic.ErrorResponse;
import com.sv.serfinsa.entity.Producto;
import com.sv.serfinsa.repository.ProductoRepository;
import com.sv.serfinsa.service.ServiceProducto;


@Service
public class ServiceProductoImpl implements ServiceProducto {

	private static Logger LOG = LoggerFactory.getLogger(ServiceProductoImpl.class);
	@Autowired
	private ProductoRepository repo;

	@Override
	public List<?> findAll() {
		LOG.info("mostrar informacion de producto");
		List<Producto> products = (List<Producto>) repo.findAll();
		return products;
	}

	@Override
	public ErrorResponse insertProduct(Producto product) {
		try {
			repo.save(product);
			return new ErrorResponse("000", "Guardado Correctamente", "Error al guardar producto");
		} 
		catch (Exception e) {
			LOG.info("error {0}", e);
			return new ErrorResponse("011", "Error generico", "Error al realizar accion");

		}
	}

	@Override
	public ErrorResponse updateProduct(Producto product, Integer id) {
		try {
			Optional<Producto> findProduct = repo.findById(id);
			
			if(findProduct.isPresent()){
				Producto udpateProd = new Producto(id, product.getNombreProducto(), product.getDescripcion(), product.getPrecio(), product.getStock(), product.getTipoProducto());
				repo.save(udpateProd);
				return new ErrorResponse("000", "Guardado Correctamente", "Error al guardar producto");
				
			} else {
				LOG.info("error al guardar datos ");
				return new ErrorResponse("017", "Error al guardar", "Error al guardar producto");
			}
		} 
		catch (Exception e) {
			LOG.info("error {0}", e);
			return new ErrorResponse("011", "Error generico", "Error al realizar accion");

		}
	}

	@Override
	public ErrorResponse deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findById(Integer id) {
					// TODO Auto-generated method stub
		return null;
	}


}
