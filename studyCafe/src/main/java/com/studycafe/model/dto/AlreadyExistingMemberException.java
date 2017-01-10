package com.studycafe.model.dto;

public class AlreadyExistingMemberException extends RuntimeException {

	public AlreadyExistingMemberException(String message) {
		super(message);
	}

}

