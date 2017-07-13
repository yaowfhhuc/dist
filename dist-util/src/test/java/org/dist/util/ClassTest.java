package org.dist.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ClassTest {

	public static void main(String[] args) {
		
		Map<String, Integer> map = new HashMap<String, Integer>() {};

		Type type = map.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
		for (Type typeArgument : parameterizedType.getActualTypeArguments()) {
		    System.out.println(typeArgument.getTypeName());
		}
		
	}
}
