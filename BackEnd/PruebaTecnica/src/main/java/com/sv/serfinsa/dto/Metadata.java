package com.sv.serfinsa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sv.serfinsa.util.Constant;

import jakarta.validation.Valid;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Metadata {
    
	@JsonProperty(value = "tipoPeticion")
	@Pattern(regexp = "^(Request|Response)$", message = "Solo accepta parametro Request")
    @NotNull(message = Constant.MSG_NULL)
	@NotEmpty(message = Constant.MSG_EMPTY)
	@Valid
	private String tipoPeticion;

	@JsonProperty(value = "fechaHora")
	@Pattern(regexp = "^([0-2]\\d|3[0-1])-(0\\d|1[0-2])-([\\d]{4})'T'([0-1]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)\\.([\\d]{3})", message = "no cumple con los requisitos de fecha formato dd-MM-yyyy'T'hh:ss:mm.sss")
	@NotNull(message = Constant.MSG_NULL)
	@NotEmpty(message = Constant.MSG_EMPTY)
	private String fechaHora;

	@Pattern(regexp = "[A-Za-z+]*",message = "solo acepta letra")
	@NotNull(message = Constant.MSG_NULL)
	@NotEmpty(message = Constant.MSG_EMPTY)
    private String idServicio;

	@Pattern(regexp = "^[A-Za-z0-9_-]*${0,100}",message = "solo acepta los siguientes caracteres letras, numeros y guiones")
	@NotNull(message = Constant.MSG_NULL)
	@NotEmpty(message = Constant.MSG_EMPTY)
	private String idTransaccion;

    public Metadata() {
    }
    
    public Metadata(
			@Pattern(regexp = "^(Request|Response)$", message = "Solo accepta parametro Request") @NotNull(message = "no contiene valor") @NotEmpty(message = "se encuentra vacio") String tipoPeticion,
			@Pattern(regexp = "^([0-2]\\d|3[0-1])-(0\\d|1[0-2])-([\\d]{4})'T'([0-1]\\d|2[0-3]):([0-5]\\d):([0-5]\\\\d)\\.([\\d]{3})", message = "no cumple con los requisitos de fecha formato dd/MM/yyyy'T'hh:ss:mm.sss") @NotNull(message = "no contiene valor") @NotEmpty(message = "se encuentra vacio") String fechaHora,
			@Pattern(regexp = "[A-Za-z+]", message = "solo acepta letra") @NotNull(message = "no contiene valor") @NotEmpty(message = "se encuentra vacio") String idServicio,
			@Pattern(regexp = "^[A-Za-z0-9_-]*${0,100}",message = "solo acepta los siguientes caracteres letras, numeros y guiones") @NotNull(message = "no contiene valor") @NotEmpty(message = "se encuentra vacio") String idTransaccion) {
		super();
		this.tipoPeticion = tipoPeticion;
		this.fechaHora = fechaHora;
		this.idServicio = idServicio;
		this.idTransaccion = idTransaccion;
	}



	public String getTipoPeticion() {
        return tipoPeticion;
    }

    public void setTipoPeticion(String tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    

}
