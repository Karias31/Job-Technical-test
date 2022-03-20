package com.barousse.project.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barousse.project.aop.Supervision;
import com.barousse.project.domain.User;
import com.barousse.project.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService implements  IUserService{
	
	@Autowired
	private UserRepository userRepository;

	
	/**
	 * Method for saving a user in the database. Fields "Name", "Country", and "Birthdate" are mandatory.
	 * User's country must be "France", and be older than 18 years old.
	 *  @param user the user we try to store in the database
	 */
	
	@Override
	@Supervision(dureeMillis = 25)
	public String saveUser(User user) {
		String result=null;
		if(userRepository.findByNameIgnoreCase(user.getName())!=null) {
			result=("User with the same name already exist");
			return result;
		}
		if(user.getCountry()!=null && user.getName()!=null && user.getBirthDate()!=null) {
			 result= validateDate(user.getBirthDate());
			
			if(result.equalsIgnoreCase("valid")) {
				result= validateAdult(user.getBirthDate());
				if(result.equalsIgnoreCase("adult")) {
					if(user.getCountry().equalsIgnoreCase("FRANCE")) {
						userRepository.save(user);
						result=("Saving  user in database");
					}else {
						result=("User must be  french");
					}		
				}
			}
		
		}else{
			result=("Name, country and birthdate are mandatory.");
		}
		return result;
		
	}
	
	/**
	 * Method to display an user's information 
	 * @return message to be displayed
	 */
	@Override
	@Supervision(dureeMillis = 25)
	public String displayUser(String name) {
		if( userRepository.findByNameIgnoreCase(name)!=null) {
			StringBuilder sb = new StringBuilder();

			sb.append("User "+name.toUpperCase()+"\n").append("Birthdate: "+userRepository.findByNameIgnoreCase(name).getBirthDate())
			.append("  Country : "+userRepository.findByNameIgnoreCase(name).getCountry()).append("  Phone : "+userRepository.findByNameIgnoreCase(name).getPhoneNumber())
			.append("  Gender : "+userRepository.findByNameIgnoreCase(name).getGender());
						
			 return sb.toString();
		}
		return ("User doesn't exist");	 		
	}
	
	
	/**
	 * method to check the dates's format (format must be dd/mm/yyyy)
	 * @param  date to check
	 * 
	 */
	private String validateDate(String date) {
		String result = null;
		if(date.length()==0){
			result = "birthdate must be in the dd/mm/yyyy format.";
            return result;
        }
        //check date format
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            Date dateN = sdf.parse(date);
            //check if date is in the past
            Date current = new Date();
            if(dateN.before(current)){
            	result = "valid";
            	return result;
            }else{
            	result =("birthdate must be in the past.");
            	return result;
            }
        } catch (java.text.ParseException e) {
        	result =("birthdate must be in the dd/mm/yyyy format.");
            return result;
        }
		
        
	}
	
	/**
	 * Method to check the user's age (user must be over 18)
	 *  @param  date to check
	 */	
	private String validateAdult(String date) {
		String result = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate birth = LocalDate.parse(date,formatter);
		
		if( Period.between(birth,  LocalDate.now() ).getYears()>17) {
			result =("adult");
		}else {
			result =("User must be 18 or older");
		}
		return result;
	}
	
}
