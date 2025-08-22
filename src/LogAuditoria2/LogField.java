package LogAuditoria2;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogField {
    String label();     // Nome do campo a exibir
    String section();   // Seção a que pertence
}