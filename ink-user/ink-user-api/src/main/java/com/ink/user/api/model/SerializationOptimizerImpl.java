//package com.yinker.user.api.model;
//
//import java.util.Collection;
//import java.util.LinkedList;
//import java.util.List;
//
//import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
//import com.yinker.user.api.model.in.ACC30010Input;
//import RetOutput;
//
///**
// * Kryo 序列化 dubbo中提升效率 ，最好将那些需要被序列化的类注册到dubbo系统中
// *
// */
//public class SerializationOptimizerImpl implements SerializationOptimizer {
//
//	@SuppressWarnings("rawtypes")
//	@Override
//	public Collection<Class> getSerializableClasses() {
//		List<Class> classes = new LinkedList<Class>();
//        classes.add(RetOutput.class);
//        classes.add(ACC30010Input.class);
//        return classes;
//	}
//}
