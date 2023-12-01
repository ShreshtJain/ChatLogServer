package com.example.ChaltLog.Server.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	  public ResourceNotFoundException(String msg) {
		    super(msg);
		  }
	  
	  public ResourceNotFoundException() {
		    super();
		  }

}
