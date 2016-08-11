package com.ink.base;


import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;

public interface IBaseManager<E, PK extends Serializable> {
	
	public E getById(PK id);

    public E getById(PK id,boolean masterMark) throws DataAccessException;


    public int save(E entity);

    public int saveBatch(List<E> list);

	public int removeById(PK id);

	public int update(E entity);

	public List<E> find(PageRequest query);


	public Page<E> findPage(PageRequest query);
}
