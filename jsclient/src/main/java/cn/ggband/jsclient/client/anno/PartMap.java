package cn.ggband.jsclient.client.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * For annotation on map
 *
 * If the value is empty, put the parameter into the public parameter package.
 * Otherwise, put the value as key into the public parameter again
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PartMap {
    String value() default "";

}
