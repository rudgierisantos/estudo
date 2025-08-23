package logAuditoria3;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	public static Map<String, Object> toMapAnotado(Object obj) {
		if (obj == null) return null;
		Map<String, Object> map = new LinkedHashMap<>();

		Class<?> clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(LogComparacao.class)) {
				field.setAccessible(true);
				try {
					Object valor = field.get(obj);
					if (valor != null) {
						if (isPrimitiveOrWrapper(valor.getClass()) || valor instanceof String) {
							map.put(field.getAnnotation(LogComparacao.class).nome(), valor);
						} else if (valor instanceof List) {
							List<?> lista = (List<?>) valor;
							List<Object> listaMap = new ArrayList<>();
							for (Object item : lista) {
								if (isPrimitiveOrWrapper(item.getClass()) || item instanceof String) {
									listaMap.add(item);
								} else {
									listaMap.add(toMapAnotado(item));
								}
							}
							map.put(field.getAnnotation(LogComparacao.class).nome(), listaMap);
						} else {
							map.put(field.getAnnotation(LogComparacao.class).nome(), toMapAnotado(valor));
						}
					} else {
						map.put(field.getAnnotation(LogComparacao.class).nome(), null);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	private static boolean isPrimitiveOrWrapper(Class<?> clazz) {
		return clazz.isPrimitive() || clazz == Integer.class || clazz == Long.class || clazz == Double.class
				|| clazz == Float.class || clazz == Boolean.class || clazz == Byte.class || clazz == Short.class
				|| clazz == Character.class;
	}
}