package com.ink.base.log.hessian.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.server.HessianSkeleton;
import com.caucho.services.server.ServiceContext;

public class YinkerHessianSkeleton extends HessianSkeleton {
	
//	private static final YinkerLoger log = YinkerLoger.getLogger(JoyHessianSkeleton.class);

	public YinkerHessianSkeleton(Object service, Class apiClass) {
		super(service, apiClass);
	}

	@Override
	public void invoke(Object service, AbstractHessianInput in,
			AbstractHessianOutput out) throws Exception {
		ServiceContext context = ServiceContext.getContext();

		// backward compatibility for some frameworks that don't read
		// the call type first
		in.skipOptionalCall();

		// Hessian 1.0 backward compatibility
		String header;
		while ((header = in.readHeader()) != null) {
			Object value = in.readObject();

			context.addHeader(header, value);
		}

		String methodName = in.readMethod();
		int argLength = in.readMethodArgLength();

		Method method;

		method = getMethod(methodName + "__" + argLength);

		if (method == null)
			method = getMethod(methodName);

		if (method != null) {
		} else if ("_hessian_getAttribute".equals(methodName)) {
			String attrName = in.readString();
			in.completeCall();

			String value = null;

			if ("java.api.class".equals(attrName))
				value = getAPIClassName();
			else if ("java.home.class".equals(attrName))
				value = getHomeClassName();
			else if ("java.object.class".equals(attrName))
				value = getObjectClassName();

			out.writeReply(value);
			out.close();
			return;
		} else if (method == null) {
			out.writeFault("NoSuchMethodException",
					"The service has no method named: " + in.getMethod(), null);
			out.close();
			return;
		}

		Class<?>[] args = method.getParameterTypes();

		if (argLength != args.length && argLength >= 0) {
			out.writeFault("NoSuchMethod",
					"method " + method
							+ " argument length mismatch, received length="
							+ argLength, null);
			out.close();
			return;
		}

		Object[] values = new Object[args.length];

		for (int i = 0; i < args.length; i++) {
			// XXX: needs Marshal object
			values[i] = in.readObject(args[i]);
		}

		Object result = null;
		
		long start = System.currentTimeMillis();

		try {
//			HessianUtil.recordHessianParam(method.getDeclaringClass().getName(), method.getName(), values);
			result = method.invoke(service, values);
		} catch (Exception e) {
//			JoyLogger logger = JoyLogger.getLogger(getClass());
			
			Throwable e1 = e;
			if (e1 instanceof InvocationTargetException)
				e1 = ((InvocationTargetException) e).getTargetException();
			
//			log.error(ModuleCode.PUBLIC_HESSIAN_SERVER, method.getName(),null,e1);

			out.writeFault("ServiceException", e1.getMessage(), e1);
			out.close();
			return;
		}finally{
			long time = System.currentTimeMillis()-start;
			
//			log.time(ModuleCode.PUBLIC_HESSIAN_SERVER, methodName, time);
//			
//			if(time > 120000){//执行时间超过2分钟告警机制
//				log.error(ModuleCode.PUBLIC_HESSIAN_TIME, methodName, methodName+"方法执行时间超过2分钟，具体时间为："+time+"ms");
//			}
//			
//			com.cmdi.b2bjoy.common.helper.ServiceContext.getResponse().addHeader(MDConst.LOG_SEQ, MDC.get(MDConst.LOG_SEQ));
		}

		// The complete call needs to be after the invoke to handle a
		// trailing InputStream
		in.completeCall();

		out.writeReply(result);

		out.close();
	}
}
