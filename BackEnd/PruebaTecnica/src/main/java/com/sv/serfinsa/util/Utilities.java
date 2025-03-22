package com.sv.serfinsa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Utilities {

	public static boolean validatePrice(String price) {
    	return Double.parseDouble(price)>0 ? true : false;
    }
	
	public static String formatoFecha(Date date, String formato){
		DateFormat format = new SimpleDateFormat(formato);
	    return format.format(date);
	}
	
}
