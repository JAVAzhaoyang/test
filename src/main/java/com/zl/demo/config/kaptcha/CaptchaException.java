package com.zl.demo.config.kaptcha;

import org.apache.shiro.authc.AuthenticationException;

/**

 * @author zy
 * @version $Revision$ 2020年
 */
public class CaptchaException extends AuthenticationException {

	/**
	 * 
	 */
	public CaptchaException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CaptchaException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CaptchaException(Throwable cause) {
		super(cause);
	}

}
