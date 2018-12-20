package com.sb.myblog.exception;

public class BlogEntryNotFoundException extends RuntimeException {
	
	static final long serialVersionUID = -7034897190745766878L;

	public BlogEntryNotFoundException(String message) {
        super(message);
    }
	
	public BlogEntryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
