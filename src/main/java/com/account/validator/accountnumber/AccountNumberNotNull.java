/**
 * 
 */
package com.account.validator.accountnumber;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Sahdev
 *
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = AccountNumberValidator.class)
@Documented
public @interface AccountNumberNotNull {

    String message() default "Account number is mandatory.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
