package com.amazon.backend.pojo;

import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.constants.GenericConstants;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordupdateAfterReset {

	@NotNull(message = AuthConstants.ERROR_EMAIL_REQUIRED)
	@Pattern(regexp = GenericConstants.EMAIL_REGEXP,message = AuthConstants.ERROR_EMAIL_IS_NOT_VALID)
	private String email;
	
	@NotNull(message = AuthConstants.ERROR_PASSWORD_REQUIRED)
	@Size(min = 8,message = AuthConstants.ERROR_PASSWORD_MIN_8_CHARS)
	private String password;
	
	@NotNull(message = AuthConstants.ERROR_OTP_REQUIRED)
	@Size(min = 6,max = 6,message = AuthConstants.ERROR_OTP_SHOULD_BE_MIN_6_CHARS)
	private String otp;
}
