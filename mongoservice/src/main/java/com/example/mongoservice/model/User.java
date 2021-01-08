package com.example.mongoservice.model;

import org.springframework.data.annotation.Transient;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Document(collection = "User")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id //Id mapping for mongodb Id to SpringId
	private BigInteger id; // if you want to map mongodb Id to springId make it big integer
	@Field("firstName") // feild name for mongodb
	private String firstName;
	@Field("lastName")
	private String lastName;
	@Field("emailId")
	 @Indexed //marks the field as indexed in MongoDB for faster traverse like in mysql, b+ tree
	private	String email;
	 @Transient // doesn't save in db// transient cannot be used
    private String yearOfBirth;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", yearOfBirth=" + yearOfBirth + "]";
	}
	
	 

}
