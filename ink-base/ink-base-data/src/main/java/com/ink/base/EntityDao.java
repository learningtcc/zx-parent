package com.ink.base;



import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;

/**
 * @author king
 */
public interface EntityDao<E, PK extends Serializable> {

	public E getById(PK id) throws DataAccessException;

    public E getById(PK id,boolean masterMark) throws DataAccessException;

	public int deleteById(PK id) throws DataAccessException;

	/** 插入数据 */
	public int save(E entity) throws DataAccessException;

    public int saveBatch(List<E> list)  throws DataAccessException;

	/** 更新数据 */
	public int update(E entity) throws DataAccessException;

	public List<E> find(PageRequest query);

	public Page<E> findPage(PageRequest query);


    public E getByEntity(E entity);
	
	public void deleteByEntity(E entity);


	
}