package com.sv.serfinsa.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sv.serfinsa.dto.ErrorResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Path.Node;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public final class ValidationRequest {


    public static <T> List<ErrorResponse> validation(T object){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        List<ErrorResponse> error = new ArrayList<>();
        

        for(ConstraintViolation<T> violation: violations){
            String nameFile = getNameFile(violation.getPropertyPath());
            String valid = violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
            String title="", code="";
            if(valid.equalsIgnoreCase("NotNull")){
                code="001";
                title="Campo sin valor";
               
            }
            else if (valid.equalsIgnoreCase("NotEmpty")) {
                code="002";
                title="Campo vacio";
               
            }
            else if(valid.equalsIgnoreCase("Pattern")){
                code="003";
                title="campo no cumple parametrizacion establecida";

            } 
            else if(valid.equalsIgnoreCase("Max")){
            	code="003";
                title="campo no cumple longitud establecida";

            }
            error.add(new ErrorResponse(code, title, nameFile.concat(" ").concat(violation.getMessage())));
            
        }

        return error;
    }

    public static String getNameFile(Path pathName){
        List<Node> nodes = new ArrayList<>();
        pathName.forEach(nodes::add);

        if (!nodes.isEmpty()) {
            return nodes.get(nodes.size()-1).getName();
        }

        return "No name";
    }
}
