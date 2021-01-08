package com.example.postservice.model;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Document(collection = "Post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	private BigInteger id;
	@Field(name ="userId" )
	private int userId;
	@Field(name ="heading" )
	private String heading;
	@Field(name ="content" )
	private String content;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", userId=" + userId + ", heading=" + heading + ", content=" + content + "]";
	}
	
	
}
