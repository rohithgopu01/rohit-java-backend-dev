package com.amazon.backend.constants;

public class AuthConstants {

	public static final String ERROR_FIRST_NAME_REQUIRED ="First name required";
	public static final String ERROR_FIRST_NAME_MIN_3_CHARS ="First name:min 3 characters";
	public static final String ERROR_LAST_NAME_REQUIRED ="Last name requried";
	public static final String ERROR_LAST_NAME_MIN_3_CHARS ="Last name:min 3 characters";
	public static final String ERROR_EMAIL_REQUIRED ="Email is required";
	public static final String ERROR_EMAIL_IS_NOT_VALID ="Email is not valid";
	public static final String ERROR_PASSWORD_REQUIRED ="Password is required";
	public static final String ERROR_PASSWORD_MIN_8_CHARS ="password min 8 characters";
	public static final String ERROR_MOBILE_REQUIRED ="Mobile is required";
	public static final String ERROR_MOBILE_MIN_10_CHARS ="Mobile min 8 characters";
	public static final String ERROR_OTP_REQUIRED ="Otp is required";
	public static final String ERROR_OTP_SHOULD_BE_MIN_6_CHARS ="Otp should be min 6 characters long";
	public static final String ERROR_USER_ALREADY_EXIST ="User already exists.Please try login";
	
    public static final String EXCEPTION_USER_NOT_FOUND="Email is not registered with us.Please signup";
    public static final String EXCEPTION_INVALID_PASSWORD="Invalid password,please try again.";
    public static final String EXCEPTION_INVALID_OTP_FOR_PASSWORD_RESET="Invalid OTP.please try again.";


    public static final String SUCCESS_LOGIN_SUCCESS_MSG="Login Success";
	public static final String SUCCESS_ACCOUNT_CREATED_MSG="Account created Successfully";
	public static final String SUCCESS_PASSWORD_UPDATED_SUCCESSFULLY="Password updated successfully.Please login";
	public static final String SUCCESS_OTP_SENT_TO_EMAIL="OTP sent to your email. Please check your Inbox/Spam folder";
	
	
}

