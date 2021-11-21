package com.volmit.adapt.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Retention(RUNTIME)
@Target({PARAMETER, TYPE, FIELD})
public @interface ArrayType {
    Class<?> type();

    int min() default 0;
}
