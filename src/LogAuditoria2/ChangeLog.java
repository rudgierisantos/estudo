package LogAuditoria2;

import java.util.*;

public class ChangeLog {
    private String section;
    private List<FieldChange> fieldChanges = new ArrayList<>();

    public ChangeLog(String section) {
        this.section = section;
    }

    public void addFieldChange(String label, Object oldValue, Object newValue) {
        fieldChanges.add(new FieldChange(label, oldValue, newValue));
    }

    public String getSection() {
        return section;
    }

    public List<FieldChange> getFieldChanges() {
        return fieldChanges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Seção: " + section + "\n");
        for (FieldChange fc : fieldChanges) {
            sb.append(" - ").append(fc).append("\n");
        }
        return sb.toString();
    }

    public static class FieldChange {
        private String label;
        private Object oldValue;
        private Object newValue;

        public FieldChange(String label, Object oldValue, Object newValue) {
            this.label = label;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }
        

        public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public Object getOldValue() {
			return oldValue;
		}

		public void setOldValue(Object oldValue) {
			this.oldValue = oldValue;
		}

		public Object getNewValue() {
			return newValue;
		}

		public void setNewValue(Object newValue) {
			this.newValue = newValue;
		}

		@Override
        public String toString() {
            return label + ": " + format(oldValue) + " -> " + format(newValue);
        }

        private String format(Object val) {
            if (val == null) return "N/A";
            if (val instanceof List<?>) return ((List<?>) val).toString();
            return String.valueOf(val);
        }
    }
}
