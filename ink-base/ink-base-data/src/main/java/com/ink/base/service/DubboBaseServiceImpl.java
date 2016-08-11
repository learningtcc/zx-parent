package com.ink.base.service;

import com.ink.base.IBaseManager;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;
import com.ink.base.utils.context.SpringApplicationContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 统一对外提供的dubbo服务，用于访问逻辑层服务
 * Created by aiyungui on 2016/7/28.
 */
@Service("dubboBaseService")
public class DubboBaseServiceImpl implements DubboBaseService {


    /**
     * 根据id获取数据
     *
     * @param beanName service beanName
     * @param id       主键ID  默认从主库查询
     * @return bean对象
     */
    @Override
    public Object getById(String beanName, Serializable id) throws Exception {
        IBaseManager iBaseManager = getIBaseManager(beanName);
        return iBaseManager.getById(id);
    }

    /**
     * 根据id获取数据
     *
     * @param beanName   service beanName
     * @param id         主键id
     * @param masterMark 是否从库查询
     * @return bean对象
     * @throws DataAccessException
     */
    @Override
    public Object getById(String beanName, Serializable id, boolean masterMark) throws Exception {
        IBaseManager iBaseManager = getIBaseManager(beanName);
        return iBaseManager.getById(id,masterMark);
    }

    /**
     * 新增数据
     *
     * @param beanName service beanName
     * @param entity   新增bean
     * @return 新增数据个数
     */
    @Override
    public int save(String beanName, Object entity) throws Exception {
        IBaseManager iBaseManager = getIBaseManager(beanName);
        return iBaseManager.save(entity);
    }

    /**
     * 批量新增数据
     *
     * @param beanName service beanName
     * @param list     新增数据集
     * @return 新增数据个数
     */
    @Override
    public int saveBatch(String beanName, List list) throws Exception {
        IBaseManager iBaseManager = getIBaseManager(beanName);
        return iBaseManager.saveBatch(list);
    }

    /**
     * 根据id删除数据
     *
     * @param beanName service beanName
     * @param id       主键ID
     * @return 删除数据个数
     */
    @Override
    public int removeById(String beanName, Serializable id) throws Exception {
        IBaseManager iBaseManager = getIBaseManager(beanName);
        return iBaseManager.removeById(id);
    }

    /**
     * 更新数据
     *
     * @param beanName service beanName
     * @param entity   bean对象
     * @return 更新数据个数
     */
    @Override
    public int update(String beanName, Object entity) throws Exception {
        IBaseManager iBaseManager = getIBaseManager(beanName);
        return iBaseManager.update(entity);
    }

    /**
     * 查找数据
     *
     * @param beanName service beanName
     * @param query    query查询对象
     * @return 符合条件的查询结果集
     */
    @Override
    public List find(String beanName, PageRequest query) throws Exception {
        IBaseManager iBaseManager = getIBaseManager(beanName);
        return iBaseManager.find(query);
    }

    /**
     * 查找数据，返回page分页对象
     *
     * @param beanName service beanName
     * @param query    query对象
     * @return page分页对象
     */
    @Override
    public Page findPage(String beanName, PageRequest query) throws Exception {

        IBaseManager iBaseManager = getIBaseManager(beanName);
        return iBaseManager.findPage(query);
    }

    /**
     * 执行动态的方法
     *
     * @param beanName   service beanName
     * @param methodName 方法名称
     * @param objects    参数对象
     * @return 由service决定
     */
    @Override
    public Object executeDynamicMethod(String beanName, String methodName, Object... objects) throws Exception {

        if (StringUtils.isBlank(methodName)){
            throw(new Exception("方法为空，请检查,methodName=" + beanName));
        }

        Object objService = SpringApplicationContext.getBean(beanName);
        if (null == objService){
            throw(new Exception("获取service对象为Null,beanName=" + beanName));
        }

        boolean isHave = false;
        Method[] methods = objService.getClass().getMethods();
        Object object = null;
        for (Method method : methods) {
            if (methodName.equals(method.getName())){
                isHave = true;

               object = method.invoke(objService,objects);
                break;
            }
        }

        if (!isHave){
            throw(new Exception(beanName + "对象中不存在"+methodName+"方法，请检查"));
        }

        return object;
    }

    /**
     * 从SpringContext中获取对象
     * @param beanName
     * @return
     * @throws Exception
     */
    private IBaseManager getIBaseManager(String beanName) throws Exception {
        IBaseManager iBaseManager = (IBaseManager) SpringApplicationContext.getBean(beanName);
        if (null == iBaseManager){
            throw(new Exception("获取service对象为Null,beanName=" + beanName));
        }

        return iBaseManager;
    }
}
