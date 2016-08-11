package com.ink.platform.imgCode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.octo.captcha.service.CaptchaServiceException;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
@Controller("imgCodeUtil")
public class ImgCodeUtil {

	
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	@RequestMapping("imagGennerate")
	@ResponseBody
	public ModelAndView ImageCaptcha(HttpServletRequest request , HttpServletResponse response) throws Exception{  
		  
        byte[] captchaChallengeAsJpeg = null;  
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();  
        try {
        	//获取sessionId
            String sessionId = request.getSession().getId();  
            BufferedImage challenge = JCaptchaServiceSingleton.getInstance()  
                    .getImageChallengeForID(sessionId, request.getLocale());  
			JPEGImageEncoder jpegEncoder = JPEGCodec  
                    .createJPEGEncoder(jpegOutputStream);  
            jpegEncoder.encode(challenge);  
        } catch (IllegalArgumentException e) {  
        } catch (CaptchaServiceException e) {  
        } catch ( ImageFormatException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();    
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");    
        response.setHeader("Cache-Control", "post-check=0, pre-check=0");    
        response.setHeader("Pragma", "no-cache");    
        response.setDateHeader("Expires", 0);    
        response.setContentType("image/jpeg");    
        ServletOutputStream responseOutputStream = response    
        		.getOutputStream();    
        responseOutputStream.write(captchaChallengeAsJpeg); 
        
        responseOutputStream.flush();    
        responseOutputStream.close();  
        return null;
    }
	/**
	 * 检查验证码
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView checkImage(HttpServletRequest request , HttpServletResponse response){
		//获取用户提交的验证码
		String code = request.getParameter("code");
		//获取用户sessionId
		String sessionId = request.getSession().getId();
		//检查用户提交的验证码正确与否
		boolean isResponseCorrect = 
                JCaptchaServiceSingleton.getInstance().
                    validateResponseForID(sessionId, code);
		System.out.println(isResponseCorrect);
		return null;
	}
}
