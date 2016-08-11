package com.ink.user.service.validate;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.model.out.RetOutput;

/**
 * 参数验证拦截器
 * 
 * @author yangchen
 * @date 2016年4月14日 下午5:14:14
 */
@Component
@Aspect
public class ValidationInterceptor {

	private static final YinkerLogger logger = YinkerLogger.getLogger(ValidationInterceptor.class);
	@Pointcut("execution(* com.ink.user.service.impl.*.exec(..))")
	private void exec() {
	}

	@Around("exec()")
	public Object validateAround(ProceedingJoinPoint joinPoint)
			throws Throwable {
		Object[] args = joinPoint.getArgs();
		BaseValidator validator = new BaseValidator(joinPoint);
		Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();  
        Method method = getMethodByClassAndName(target.getClass(), methodName);    //得到拦截的方法  
        try{
        	validator.validate(methodName, method.getParameterTypes(), args);
        }catch(Exception e){
        	logger.error("参数验证失败", e.getMessage());
        	RetOutput rt = (RetOutput) method.getReturnType().newInstance();
        	rt.setRetCode(RespCodeConstant.RespCode_000002);
        	rt.setRetMsg(RespCodeConstant.RespCode_000002Desc + e.getMessage());
        	return rt;
        }
		return joinPoint.proceed();
	}
	
	/** 
     * 根据类和方法名得到方法 
     */  
    public Method getMethodByClassAndName(Class<?> c , String methodName){  
        Method[] methods = c.getDeclaredMethods();  
        for (Method method : methods) {  
            if(method.getName().equals(methodName)){  
                return method ;  
            }  
        }  
        return null;  
    } 
}
