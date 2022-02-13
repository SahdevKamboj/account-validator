/**
 * 
 */
package com.account.validator.accountnumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Sahdev
 *
 */
public class AccountNumberValidator implements ConstraintValidator<AccountNumberNotNull, Long> {

	@Override
	public boolean isValid(Long pValue, ConstraintValidatorContext pContext) {

		return pValue != null;
	}
}
