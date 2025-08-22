package LogAuditoria2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LogComparator {

	public static List<ChangeLog> compare(Object oldObj, Object newObj) throws Exception {
		List<ChangeLog> logs = new ArrayList<>();

		if (oldObj == null && newObj == null)
			return logs;

		Class<?> clazz = oldObj != null ? oldObj.getClass() : newObj.getClass();

		// Agrupa mudanças por seção
		Map<String, ChangeLog> sectionMap = new LinkedHashMap<>();

		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(LogField.class)) {
				Object oldValue = oldObj != null ? method.invoke(oldObj) : null;
				Object newValue = newObj != null ? method.invoke(newObj) : null;

				if (!Objects.equals(oldValue, newValue)) {
					LogField annotation = method.getAnnotation(LogField.class);

					ChangeLog log = sectionMap.computeIfAbsent(annotation.section(), ChangeLog::new);
					log.addFieldChange(annotation.label(), oldValue, newValue);
				}
			}
		}

		logs.addAll(sectionMap.values());
		return logs;
	}
}