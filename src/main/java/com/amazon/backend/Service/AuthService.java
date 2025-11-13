package com.amazon.backend.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazon.backend.Entity.User;
import com.amazon.backend.Exceptions.InvalidOtpforPasswordResetException;
import com.amazon.backend.Exceptions.InvalidPasswordException;
import com.amazon.backend.Exceptions.UserAlreadyExistException;
import com.amazon.backend.Exceptions.UserNotFoundException;
import com.amazon.backend.Repository.UserRepository;
import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.enums.UserRole;
import com.amazon.backend.pojo.ForgotPasswordSendOTP;
import com.amazon.backend.pojo.Logindata;
import com.amazon.backend.pojo.PasswordupdateAfterReset;
import com.amazon.backend.pojo.SignupData;
import com.amazon.backend.utils.Authutility;

@Service
public class AuthService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private EmailService emailService;

	public PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


	public Map<String, Object> signup(SignupData signupData) {
		
		Optional<User> dbOptional=userRepository.findByEmail(signupData.getEmail());
		if (dbOptional.isPresent()) {
			throw new UserAlreadyExistException(AuthConstants.ERROR_USER_ALREADY_EXIST);
			
		}
		User user = new User();
		user.setFirstName(signupData.getFirstName());
		user.setLastName(signupData.getLastName());
		user.setEmail(signupData.getEmail());
		user.setPasswordHash(passwordEncoder.encode(signupData.getPassword()));
		user.setMobile(signupData.getMobile());
		user.setRole(UserRole.BUYER);
		
		user= userRepository.save(user);
		String token =jwtService.generatedJwtToken(user);
		Map<String, Object> responseMap=new HashMap<String, Object>();
		responseMap.put("userData", user);
		responseMap.put("token", token);
		
		return responseMap;
		
	}
	public Map<String, Object> login(Logindata logindata) {
		Optional<User> dbOptional=userRepository.findByEmail(logindata.getEmail());
		if (dbOptional.isEmpty()) {
			throw new UserNotFoundException(AuthConstants.EXCEPTION_USER_NOT_FOUND);
		}
		User user=dbOptional.get();
		if(passwordEncoder.matches(logindata.getPassword(),user.getPasswordHash()) == false){
			throw new InvalidPasswordException(AuthConstants.EXCEPTION_INVALID_PASSWORD);
		}
		
		String token =jwtService.generatedJwtToken(user);
		Map<String, Object> responseMap=new HashMap<String, Object>();
		responseMap.put("userData", user);
		responseMap.put("token", token);
		return responseMap;
	}
	public void forgotPassword(ForgotPasswordSendOTP forgotPasswordSendOTP) {
		Optional<User> dbOptional=userRepository.findByEmail(forgotPasswordSendOTP.getEmail());
		if (dbOptional.isEmpty()) {
			throw new UserNotFoundException(AuthConstants.EXCEPTION_USER_NOT_FOUND);	
		}
		//otp generator
		User user=dbOptional.get();
		int otp =Authutility.generateOtp();
		String emailbody ="Hi " + user.getFirstName() + "," +
		"Please use below OTP to reset your password, OTP: " + otp + "." +
		"Thank you.";
		emailService.sendPlainEmail("Otp to reset your password:test email from API", emailbody,"rohithgopu01@gmail.com", user.getEmail());
		
		user.setOtp(otp);
		userRepository.save(user);
	}
	
     public void passwordUpdateAfterReset(PasswordupdateAfterReset passwordupdateAfterReset) {
    	 
    	 Optional<User> dbOptional=userRepository.findByEmail(passwordupdateAfterReset.getEmail());
 		if (dbOptional.isEmpty()) {
 			throw new UserNotFoundException(AuthConstants.EXCEPTION_USER_NOT_FOUND);	
 		}	 
 		User user=dbOptional.get();
 		if (user.getOtp() != Integer.parseInt(passwordupdateAfterReset.getOtp())) {
			throw new InvalidOtpforPasswordResetException(AuthConstants.EXCEPTION_INVALID_OTP_FOR_PASSWORD_RESET);
		}
 		user.setPasswordHash( passwordEncoder.encode(passwordupdateAfterReset.getPassword()));
 		user.setOtp(0);
 		userRepository.save(user);
	}

}
