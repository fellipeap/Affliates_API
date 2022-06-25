package com.api.afiliados.validation;

import com.api.afiliados.models.TpoCadastroEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class ValidTpoCadastro implements ConstraintValidator<TpoCadastro, String> {

    @Override
    public void initialize(TpoCadastro constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        TpoCadastroEnum tipoCadastroEnum = TpoCadastroEnum.valueOf(value.toUpperCase(Locale.ROOT));
        switch (tipoCadastroEnum){
            case CURSO:
            case ITEM_CURSO:
                return true;
        }
        return false;
    }
}
