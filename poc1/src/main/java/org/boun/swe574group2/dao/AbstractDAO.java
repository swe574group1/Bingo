package org.boun.swe574group2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.boun.swe574group2.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDAO<E extends Entity>
{
	private final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

	protected Connection connection = null;

	private final String tableName;
	private final Class<E> entityClass;

	public AbstractDAO(String tableName, Class<E> entityClass)
	{
		this.tableName = tableName;
		this.entityClass = entityClass;

		try {
			String userName = "swe574group2";
			String password = "swe574group2";
			String url = "jdbc:mysql://10.0.0.10/swe574group2";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, userName, password);
			logger.info("Connected to db.");
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to db", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void close()
	{
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	// JDBC wrappers -----

	protected List<Map<String, Object>> query(String sql, Object... params)
	{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			int i = 1;
			for (Object param : params) {
				preparedStatement.setObject(i++, param);
			}

			ResultSet rs = preparedStatement.executeQuery();
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				ResultSetMetaData md = rs.getMetaData();
				Map<String, Object> row = new HashMap<String, Object>();
				for (i = 1; i <= md.getColumnCount(); i++) {
					row.put(md.getColumnName(i), rs.getObject(i));
				}
				rows.add(row);
			}
			rs.close();
			preparedStatement.close();
			return rows;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected void update(String sql)
	{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// common DAO methods -----

	public List<E> loadAll()
	{
		List<Map<String, Object>> rows = query("SELECT * FROM " + tableName);
		return RowMapper.bind(rows, entityClass);
	}

	public E load(int id)
	{
		List<Map<String, Object>> rows = query("SELECT * FROM " + tableName + " WHERE id = ?", id);
		return getFirst(RowMapper.bind(rows, entityClass));
	}

	public void save(E entity)
	{
//		if (load(entity.getId()) == null) {
//			update("INSERT INTO " + tableName + " ("+getPlaceholders()+") VALUES (?)");
//		} else {
//			update("UPDATE " + tableName + " SET");
//		}
	}

	protected <T> T getFirst(List<T> rows)
	{
		if (rows.isEmpty()) {
			return null;
		} else {
			return rows.get(0);
		}
	}

	private String getPlaceholders(int count)
	{
		return StringUtils.repeat("?", ",", count);
	}
}
