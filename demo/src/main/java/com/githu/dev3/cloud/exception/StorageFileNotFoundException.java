package com.githu.dev3.cloud.exception;

@SuppressWarnings("serial")
public class StorageFileNotFoundException extends StorageException {
	
	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
