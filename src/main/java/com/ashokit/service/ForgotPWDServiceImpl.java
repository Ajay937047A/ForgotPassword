package com.ashokit.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.Repository.ForgotPWDRepository;
import com.ashokit.constant.AppConstant;
import com.ashokit.entity.UserEntity;
import com.ashokit.utils.EmailUtils;

@Service
public class ForgotPWDServiceImpl implements ForgotService {

	
	@Autowired
	private ForgotPWDRepository forgotPWDRepository;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public String getEmail( String userEmail) {
		UserEntity entity = forgotPWDRepository.findByUserEmail(userEmail);
		
		
		
		if(entity==null) {
			return AppConstant.INVALID_USER;
		}
		if(entity!=null) {
			sendEmail(entity);
			
			
			return AppConstant.FORGOT_PASSWORD_SUCCESS;
		}
		

		return AppConstant.INTERNAL_SERVER_ERROR;
	}

	private boolean  sendEmail(UserEntity entity) {
		boolean emailSend=false;
		String subject=AppConstant.USER_FORGOT_PASSWORD;
		String body=readMailBody(AppConstant.UNLOCK_ACC_EMAIL_BODY_TEMPLATE, entity);
		
		emailUtils.sendFogotMail(subject, body, entity.getUserEmail());
		
		return emailSend;
	}
	
	
	public String readMailBody(String fileName, UserEntity entity) {
		String mailBody=null;
		StringBuffer buffer=new StringBuffer();
		Path path = Paths.get(fileName);
		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach(line->{
				buffer.append(line);
			});
			mailBody=buffer.toString();
			mailBody= mailBody.replace("{FNAME}", entity.getUserFirstName());
			mailBody= mailBody.replace("{PWD}", Integer.toString(entity.getUserPWD()));
			return mailBody;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Ajay readMailBody Exception");
		}
		
		return mailBody;
	}
	


	

}
