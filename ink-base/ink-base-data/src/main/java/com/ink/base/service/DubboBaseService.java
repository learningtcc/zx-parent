package com.ink.base.service;

import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;

/**
 * 统一对外提供的dubbo服务，用于访问逻辑层服务
 * Created by aiyungui on 2016/7/28.
 */
public interface DubboBaseService<E, PK extends Serializable> {

    /**
     * 根据id获取数据
     * @param beanName service beanName
     * @param id 主键ID  默认从主库查询
     * @return bean对象
     */
    public E getById(String beanName,PK id) throws Exception;

    /**
     * 根据id获取数据
     * @param beanName service beanName
     * @param id  主键id
     * @param masterMark 是否从库查询
     * @return bean对象
     * @throws DataAccessException
     */
    public E getById(String beanName,PK id,boolean masterMark) throws Exception;

    /**
     * 新增数据
     * @param beanName service beanName
     * @param entity 新增bean
     * @return 新增数据个数
     */
    public int save(String beanName,E entity) throws Exception;

    /**
     * 批量新增数据
     * @param beanName service beanName
     * @param list 新增数据集
     * @return 新增数据个数
     */
    public int saveBatch(String beanName,List<E> list) throws Exception;

    /**
     * 根据id删除数据
     * @param beanName service beanName
     * @param id 主键ID
     * @return 删除数据个数
     */
    public int removeById(String beanName,PK id) throws Exception;

    /**
     * 更新数据
     * @param beanName service beanName
     * @param entity bean对象
     * @return 更新数据个数
     */
    public int update(String beanName,E entity) throws Exception;

    /**
     * 查找数据
     * @param beanName service beanName
     * @param query query查询对象
     * @return 符合条件的查询结果集
     */
    public List<E> find(String beanName,PageRequest query) throws Exception;

    /**
     * 查找数据，返回page分页对象
     * @param beanName service beanName
     * @param query query对象
     * @return page分页对象
     */
    public Page<E> findPage(String beanName,PageRequest query) throws Exception;

    /**
     *执行动态的方法
     * @param beanName service beanName
     * @param methodName 方法名称
     * @param objects  参数对象
     * @return 由service决定
     */
    public Object executeDynamicMethod(String beanName,String methodName,Object ... objects) throws Exception;
}
