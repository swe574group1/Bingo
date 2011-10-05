package org.boun.swe574group2.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RowMapper
{
	private final static Logger logger = LoggerFactory.getLogger(RowMapper.class);

	public static <T> List<T> bind(List<Map<String, Object>> rows, Class<T> cls)
	{
		List<T> entities = new ArrayList<T>();
		for (Map<String, Object> row : rows) {
			entities.add(bind(row, cls));
		}
		return entities;
	}

	public static <T> T bind(Map<String, Object> row, Class<T> cls)
	{
		T entity = getInstance(cls);
		BeanMap beanMap = new BeanMap(entity);
		for (Entry<String, Object> entry : row.entrySet()) {
			try {
				beanMap.put(entry.getKey(), entry.getValue());
			} catch (IllegalArgumentException e) {
				logger.error("cannot bind column[{}] to object property", entry.getKey());
			}
		}
		return entity;
	}

	private static <T> T getInstance(Class<T> cls)
	{
		try {
			return cls.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
