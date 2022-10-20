package com.sca.controller;

import java.io.Serializable; 
public class JwtResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;
   
	private final String token;
   
	public JwtResponseModel(String token) {
		System.out.println("5");
      this.token = token;
   }
	
   public String getToken() {
      return token;
   }
}