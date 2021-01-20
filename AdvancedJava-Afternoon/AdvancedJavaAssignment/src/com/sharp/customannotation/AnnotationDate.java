package com.sharp.customannotation;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class AnnotationDate {
	
public static void addDate(Object object) throws IllegalArgumentException, IllegalAccessException {
		
		Class<?> cls = object.getClass();
		
		for(Field field : cls.getDeclaredFields()) {
			field.setAccessible(true);
			
			if(field.isAnnotationPresent(Adate.class)) {
				Adate date = field.getAnnotation(Adate.class);
				field.set(object, LocalDate.now().toString());
			}
		}
		
	}

}
