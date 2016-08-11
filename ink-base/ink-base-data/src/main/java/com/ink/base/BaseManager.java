package com.ink.base;



import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


/**
 * @author king
 */

public abstract class BaseManager<E, PK extends Serializable> implements IBaseManager<E, PK>{
   

    private Logger log = LoggerFactory.getLogger(BaseManager.class);


    protected abstract EntityDao<E, PK> getEntityDao();
    @Transactional(readOnly = true)
    public E getById(PK id) throws DataAccessException {
        return getEntityDao().getById(id);
    }

    @Transactional(readOnly = true)
    public E getById(PK id,boolean masterMark) throws DataAccessException {
        return getEntityDao().getById(id);
    }

    /** 插入数据 */
    
    public int save(E entity) throws DataAccessException {
        return  getEntityDao().save(entity);
    }


    public int saveBatch(List<E>list)  throws DataAccessException{
        return getEntityDao().saveBatch(list);
    }


    public int removeById(PK id) throws DataAccessException {
      return  getEntityDao().deleteById(id);
    }

    
    public int update(E entity) throws DataAccessException {
       return getEntityDao().update(entity);
    }

    @Transactional(readOnly = true)
    public List<E> find(PageRequest query) {
        return getEntityDao().find(query);
    }


    public Page<E> findPage(PageRequest query) {
        return getEntityDao().findPage(query);
    }


    
    public void deleteByEntity(E entity) throws DataAccessException {
        getEntityDao().deleteByEntity(entity);
    }


    public E getByEntity(E entity) throws DataAccessException {
        return getEntityDao().getByEntity(entity);
    }

   
}
