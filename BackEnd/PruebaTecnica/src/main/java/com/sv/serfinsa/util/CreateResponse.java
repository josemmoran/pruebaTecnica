package com.sv.serfinsa.util;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sv.serfinsa.dto.DataResponse;
import com.sv.serfinsa.dto.Metadata;
import com.sv.serfinsa.dto.Response;


public final class CreateResponse {
    
    private CreateResponse(){
        throw new IllegalStateException("CreationResponse class");
    }

    public static ResponseEntity<Object> returnResponseError(Metadata metadata, Object error){
        return new ResponseEntity<>(createResponse(transforResponse(metadata), error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> returnBadRequest(Metadata metadata,Object data){
        return new ResponseEntity<>(createResponse(transforResponse(metadata), data), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> returnOk(Metadata metadata,Object data){
        return new ResponseEntity<>(createResponse(transforResponse(metadata), data), HttpStatus.OK);
    }
    private static Response createResponse(Metadata metadata,Object body){
        return new Response(metadata, new DataResponse(body));
    }

    private static Metadata transforResponse(Metadata metadata){
        metadata.setFechaHora(Utilities.formatoFecha(new Date(), "dd-MM-yyyy'T'hh:mm:ss"));
        metadata.setTipoPeticion("Response");
        return metadata;
    }

}
