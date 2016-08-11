package com.ink.platform.imgCode;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 校验验证码
 * @author lww
 *
 */
public class JCaptchaServiceSingleton {
	 private static ImageCaptchaService imageCaptchaService = 
	        new DefaultManageableImageCaptchaService(  
	                new FastHashMapCaptchaStore(), new MyImageCaptchaEngine(), 180,  
	                100000 , 75000);
	
	public static ImageCaptchaService getInstance(){
	    return imageCaptchaService;
	} 
}
