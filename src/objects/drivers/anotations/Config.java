package objects.drivers.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import objects.drivers.enums.BoxType;

/**
 *
 * @author dogiloki
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Config{

    String id() default("");
    String label();
    BoxType box();
    String[] options() default("");
    
}
