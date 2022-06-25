package com.api.afiliados.validation;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidTpoCadastro.class)
public @interface TpoCadastro {
    String message() default "Tipo de castro desconhecio.";
}
