package org.aggregatortech.dindora.topic.interceptor.annotation;

import org.glassfish.hk2.extras.interception.InterceptionBinder;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@InterceptionBinder
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@Documented
public @interface RequiresAuthorization {
}
