package com.barousse.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter  @Setter  @NoArgsConstructor
public class User {
	/**
	 * unique Id for the user
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/**
	 * name of the user
	 */
	@NonNull
	private String name;
	/**
	 * country of residence of the user
	 */
	@NonNull
	private String country;	
	
	/**
	 * birthDate of the user, in dd/mm/yyyy format
	 */
	@NonNull
	private String birthDate;
	/**
	 * phoneNumber of the user
	 */
	private String phoneNumber;
	/**
	 * gender of the user
	 */
	private String gender;
	
	public User(Long id, String name, String country, String birthDate, String phoneNumber, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	
	
	
}
