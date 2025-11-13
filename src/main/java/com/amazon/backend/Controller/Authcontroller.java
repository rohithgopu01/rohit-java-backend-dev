package com.amazon.backend.Controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.amazon.backend.Entity.User;
import com.amazon.backend.Service.AuthService;
import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.ForgotPasswordSendOTP;
import com.amazon.backend.pojo.Logindata;
import com.amazon.backend.pojo.PasswordupdateAfterReset;
import com.amazon.backend.pojo.SignupData;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class Authcontroller {
	
	@Autowired
	private  AuthService authService;
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<User>> signup(@Valid @RequestBody SignupData signupData) {
		Map<String, Object>  data = authService.signup(signupData);
		ApiResponse<User> apiResponse=new ApiResponse<User>(true,AuthConstants.SUCCESS_ACCOUNT_CREATED_MSG, (User) data.get("userData"));
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Authorization","Bearer " + data.get("token").toString());
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiResponse);
	}
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<User>> login(@Valid @RequestBody Logindata logindata ) {
		Map<String, Object>  data = authService.login(logindata);
		ApiResponse<User> apiResponse=new ApiResponse<User>(true,AuthConstants.SUCCESS_LOGIN_SUCCESS_MSG, (User) data.get("userData"));
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Authorization","Bearer " + data.get("token").toString());
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiResponse);
	}
	@PostMapping("/Forgot-Password/send-Otp")
	public ResponseEntity<ApiResponse<String>> forgotPassword(@Valid @RequestBody ForgotPasswordSendOTP forgotPasswordSendOTP) {
		authService.forgotPassword(forgotPasswordSendOTP);
		ApiResponse<String> apiResponse=new ApiResponse<String>(true,AuthConstants.SUCCESS_OTP_SENT_TO_EMAIL,"");
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		}
	@PostMapping("/Forgot-Password/update-Password")
	public ResponseEntity<ApiResponse<String>> updatePasswordAfterReset(@Valid @RequestBody PasswordupdateAfterReset passwordupdateAfterReset ){
		authService.passwordUpdateAfterReset(passwordupdateAfterReset);
		ApiResponse<String> apiResponse=new ApiResponse<String>(true,AuthConstants.SUCCESS_PASSWORD_UPDATED_SUCCESSFULLY,"");
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
