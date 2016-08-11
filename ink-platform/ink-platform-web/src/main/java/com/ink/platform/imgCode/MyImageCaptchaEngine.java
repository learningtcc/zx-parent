/**
 * CttouziImageCaptchaEngine.java
 */
package com.ink.platform.imgCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * 验证码生成器
 * @author lww
 *
 */
public class MyImageCaptchaEngine extends ListImageCaptchaEngine {
	
	protected void buildInitialFactories() {
		Integer minWordLength = 4;
		Integer maxWordLength = 4;
		Integer fontSize = 12;
		Integer imageWidth = 90;
		Integer imageHeight = 50;
		Integer minFontSize = 26;
		Integer maxFontSize = 26;
		String randomString = "123456789abcdefghijkmnopqrstuvwxyz";
        WordGenerator wordGenerator = new RandomWordGenerator(randomString);  
        RandomRangeColorGenerator rdColGen = new RandomRangeColorGenerator(
        		       new int[] { 0, 200 }, new int[] { 0, 200 }, 
        		       new int[] { 0, 200 });
//        TextPaster randomPaster = new DecoratedRandomTextPaster(minWordLength,  
//                maxWordLength, new RandomListColorGenerator(new Color[] {  
//                        new Color(23, 170, 27), new Color(220, 34, 11),  
//                        new Color(23, 67, 172) }), new TextDecorator[] {});
        TextPaster randomPaster = new RandomTextPaster(minWordLength,  
                maxWordLength, rdColGen, Boolean.valueOf(true));  
        BackgroundGenerator background = new UniColorBackgroundGenerator(  
                imageWidth, imageHeight, Color.white); 
        Font[] fonts = { new Font("nyala", Font.BOLD, fontSize),  
                new Font("Bell MT", Font.PLAIN, fontSize),  
                new Font("Credit valley", Font.BOLD, fontSize),
        		new Font("Verdana",Font.BOLD,fontSize)};
        FontGenerator font = new RandomFontGenerator(minFontSize, maxFontSize,fonts);  
  
        ImageDeformation postDef = new ImageDeformationByFilters(  
                new ImageFilter[] {});  
        ImageDeformation backDef = new ImageDeformationByFilters(  
                new ImageFilter[] {});  
        ImageDeformation textDef = new ImageDeformationByFilters(  
                new ImageFilter[] {});  
  
        WordToImage wordimage = new DeformedComposedWordToImage(font,  
                background, randomPaster, backDef, textDef, postDef);  
        addFactory(new GimpyFactory(wordGenerator, wordimage));  
  
    }

}
