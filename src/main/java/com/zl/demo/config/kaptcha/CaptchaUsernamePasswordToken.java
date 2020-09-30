package com.zl.demo.config.kaptcha;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author zy
 * @version $Revision$ 2020年
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * 
	 */
	public CaptchaUsernamePasswordToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @param host
	 */
	public CaptchaUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host,
                                        String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}
}
