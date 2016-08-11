package com.ink.trade.service.validate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ink.trade.api.model.BaseTrade;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.IMchAuthManager;
import com.ink.trade.core.po.MchAuth;

/**
 * 参数验证拦截器
 * 
 * @author yangchen
 * @date 2016年4月14日 下午5:14:14
 */
@Component
@Aspect
public class ValidationInterceptor {
    @Autowired
    private IMchAuthManager mchAuthManger;
	@Pointcut("execution(* com.yinker.trade.service.impl.*.*(..))")
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
        	mchAuthValidate(method,args);
        }catch(Exception e){
        	Class classz =method.getReturnType();
        	Object object=classz.newInstance();
        	Field field=getDeclaredField(object,"reponseCode");
        	field.setAccessible(true);
        	field.set(object, TradeRespConstant.TRADE_ERROR_0001);
        	Field field1=getDeclaredField(object,"reponseMsg");
        	field1.setAccessible(true);
        	field1.set(object, TradeRespConstant.TRADE_ERROR_0001_MSG + e.getMessage());
//        	BaseTradeOutput rs = (BaseTradeOutput) method.getReturnType().newInstance();
//        	rs.setReponseCode(TradeRespConstant.TRADE_ERROR_0001);
//        	rs.setReponseMsg(TradeRespConstant.TRADE_ERROR_0001_MSG + e.getMessage());
        	return object;
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
    private void mchAuthValidate(Method method,Object[] args)throws Exception{
        Class [] parameterType=  method.getParameterTypes();
        if(parameterType.length>0&&parameterType[0].newInstance() instanceof BaseTrade ){
            BaseTrade baseTrade=(BaseTrade) args[0];
            MchAuth mchAuth=  mchAuthManger.getByMchIdPayType(baseTrade.getMerchantId(), baseTrade.getPayType());
            if(mchAuth==null){
                throw new TradeException("商户"+baseTrade.getMerchantId()+"未开通"+baseTrade.getPayType()+"支付方式");
            }
            System.out.println(baseTrade.getMerchantId());
       }
    }
    /**
     * 
     * @param object
     * @param fieldName
     * @return
     */
    public  Field getDeclaredField(Object object, String fieldName){  
        Field field = null ;  
          
        Class<?> clazz = object.getClass() ;  
          
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {  
                field = clazz.getDeclaredField(fieldName) ;  
                return field ;  
            } catch (Exception e) {  
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。  
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了  
                  
            }   
        }  
      
        return null;  
    }     
}
