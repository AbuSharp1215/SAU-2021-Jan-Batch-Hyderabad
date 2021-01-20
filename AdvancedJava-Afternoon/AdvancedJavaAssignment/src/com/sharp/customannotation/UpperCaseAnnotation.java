package com.sharp.customannotation;

import java.lang.reflect.Field;

public class UpperCaseAnnotation {
	
	public static void toUpperCase(Object object) throws IllegalArgumentException, IllegalAccessException {
		
		Class<?> cls = object.getClass();
		
		for(Field field : cls.getDeclaredFields()) {
			field.setAccessible(true);
			
			if(field.isAnnotationPresent(UpperCase.class)) {
				UpperCase upperCase = field.getAnnotation(UpperCase.class);
				field.set(object, field.get(object).toString().toUpperCase());
			}
		}
		
	}
}
