package LogAuditoria;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class LogService {

	public List<Log> comparar(Object objetoAnterior, Object novoObjeto) {
		return compararObjetos(objetoAnterior, novoObjeto, "");
	}
	
	public List<Log> comparar(Object objetoAnterior, Object novoObjeto, String path) {
		return compararObjetos(objetoAnterior, novoObjeto, path);
	}

	private List<Log> compararObjetos(Object objetoAnterior, Object novoObjeto, String path) {
		List<Log> diferencas = new ArrayList<>();

		if (objetoAnterior == null && novoObjeto == null)
			return diferencas;
		if (objetoAnterior == null || novoObjeto == null) {
			diferencas.add(new Log(path, objetoAnterior, novoObjeto));
			return diferencas;
		}

		Class<?> clazz = objetoAnterior.getClass();
		if (!clazz.equals(novoObjeto.getClass())) {
			diferencas.add(new Log(path, objetoAnterior, novoObjeto));
			return diferencas;
		}

		// Campos simples
		if (isSimpleType(clazz)) {
			if (!Objects.equals(normalize(objetoAnterior), normalize(novoObjeto))) {
				diferencas.add(new Log(path, objetoAnterior, novoObjeto));
			}
			return diferencas;
		}

		// Lista
		if (objetoAnterior instanceof List && novoObjeto instanceof List) {
			List<?> oldList = (List<?>) objetoAnterior;
			List<?> newList = (List<?>) novoObjeto;

			int max = Math.max(oldList.size(), newList.size());
			for (int i = 0; i < max; i++) {
				Object oVal = i < oldList.size() ? oldList.get(i) : null;
				Object nVal = i < newList.size() ? newList.get(i) : null;
				diferencas.addAll(compararObjetos(oVal, nVal, path + "[" + i + "]"));
			}
			return diferencas;
		}

		// Map
		if (objetoAnterior instanceof Map && novoObjeto instanceof Map) {
			Map<?, ?> oldMap = (Map) objetoAnterior;
			Map<?, ?> newMap = (Map<?, ?>) novoObjeto;

			Set<Object> keys = new HashSet<>();
			keys.addAll(oldMap.keySet());
			keys.addAll(newMap.keySet());

			for (Object key : keys) {
				Object oVal = oldMap.get(key);
				Object nVal = newMap.get(key);
				diferencas.addAll(compararObjetos(oVal, nVal, path + "[" + key + "]"));
			}
			return diferencas;
		}

		// Objeto complexo com campos anotados
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.isAnnotationPresent(GravaLog.class))
				continue;
			field.setAccessible(true);
			try {
				Object oldVal = field.get(objetoAnterior);
				Object newVal = field.get(novoObjeto);
				String fieldPath = path.isEmpty() ? field.getName() : path + "." + field.getName();
				diferencas.addAll(compararObjetos(oldVal, newVal, fieldPath));
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Erro ao acessar campo: " + field.getName(), e);
			}
		}

		return diferencas;
	}

	private boolean isSimpleType(Class<?> clazz) {
		return clazz.isPrimitive() || Number.class.isAssignableFrom(clazz) || clazz.equals(String.class)
				|| clazz.equals(Boolean.class) || clazz.equals(Character.class) || clazz.equals(BigDecimal.class)
				|| Date.class.isAssignableFrom(clazz);
	}

	private Object normalize(Object value) {
		if (value instanceof BigDecimal)
			return ((BigDecimal) value).stripTrailingZeros();
		return value;
	}

}
