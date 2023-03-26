package objects.drivers.anotations;

import objects.drivers.enums.BoxType;

/**
 *
 * @author dogiloki
 */

public @interface Config{

    String label();
    BoxType box();
    String[] options() default("");
    
}
