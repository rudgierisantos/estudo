package LogAuditoria2;

import java.lang.reflect.Field;
import java.util.*;

public class LogComparator {

    public static List<ChangeLog> compare(Object oldObj, Object newObj) throws IllegalAccessException {
        Map<String, ChangeLog> sectionMap = new LinkedHashMap<>();

        Class<?> clazz = oldObj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(LogField.class)) {
                field.setAccessible(true);
                LogField annotation = field.getAnnotation(LogField.class);

                Object oldValue = field.get(oldObj);
                Object newValue = field.get(newObj);

                if (!Objects.equals(oldValue, newValue)) {
                    String section = annotation.section();
                    sectionMap.putIfAbsent(section, new ChangeLog(section));
                    sectionMap.get(section).addFieldChange(annotation.label(), oldValue, newValue);
                }
            }
        }

        return new ArrayList<>(sectionMap.values());
    }
}