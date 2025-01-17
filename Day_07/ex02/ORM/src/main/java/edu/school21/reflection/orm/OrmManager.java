package edu.school21.reflection.orm;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class OrmManager {
	private HikariDataSource dataSource;
	private String schema;

	public OrmManager(String schema, String packageName) {
		this.schema = schema;
		createConfig();
		Set<Class> classes = OrmUtils.findAllClasses(packageName, schema);
		classes = classes.stream().map(c -> {
			if (c.getAnnotation(OrmEntity.class) != null) {
				return c;
			}
			return null;
		}).filter(Objects::nonNull).collect(Collectors.toSet());
		for (Class currClass : classes) {
			List<Field> fields = Arrays.stream(currClass.getDeclaredFields()).map(f -> {
				if (f.getAnnotation(OrmColumn.class) != null
						|| f.getAnnotation(OrmColumnId.class) != null) {
					return f;
				}
				return null;
			}).filter(Objects::nonNull).collect(Collectors.toList());
			String query = OrmUtils.createTableQuery((OrmEntity)currClass.getAnnotation(OrmEntity.class), fields);
			exeUpdateQuery(query, null);
		}
	}

	private void createConfig() {
		HikariConfig cfg = new HikariConfig();
		cfg.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
		cfg.setUsername("postgres");
		cfg.setPassword("qwerty");
		dataSource = new HikariDataSource(cfg);
		exeUpdateQuery("drop schema if exists " + schema + " cascade;", null);
		exeUpdateQuery("create schema if not exists " + schema + ";", null);
	}

	private void exeUpdateQuery(String query, Object entity) {
		try (Connection con = dataSource.getConnection();
			Statement st = con.createStatement()) {
			if (entity != null) {
				try {
					ResultSet rs = st.executeQuery(query);

					if (rs.next()) {
						Field field = getIdField(entity);
						field.set(entity, rs.getLong(1));
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.err.println(e.getMessage());
				}
			} else {
				st.executeUpdate(query);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void save(Object entity) {
		StringBuilder stBuilder = new StringBuilder("INSERT INTO " + schema + "."
				+ entity.getClass().getAnnotation(OrmEntity.class).table() + "() VALUES () RETURNING id;");
		List<Field> fields = getColumnFields(entity);
		for (Field field : fields) {
			field.setAccessible(true);
			String type = field.getAnnotation(OrmColumn.class).name();
			stBuilder.insert(stBuilder.indexOf(")"), type + ",");
			try {
				String value = field.get(entity).toString();

				if (field.getType().getName().contains("String")) {
					value = "'" + value + "'";
				}
				stBuilder.insert(stBuilder.lastIndexOf(")"), value + ",");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.err.println(e.getMessage());
			}
		}
		String query = stBuilder.deleteCharAt(stBuilder.indexOf(")") - 1)
				.deleteCharAt(stBuilder.lastIndexOf(",")).toString();
		exeUpdateQuery(query, entity);
	}

	public void update(Object entity) {
		StringBuilder stBuilder = new StringBuilder("UPDATE " + schema + "."
				+ entity.getClass().getAnnotation(OrmEntity.class).table() + " SET ");
		List<Field> fields = getColumnFields(entity);
		for (Field field : fields) {
			field.setAccessible(true);
			String type = field.getAnnotation(OrmColumn.class).name();
			stBuilder.append(type).append(" = ");
			try {
				String value;

				if (field.get(entity) == null) {
					value = null;
				} else {
					value = field.get(entity).toString();
				}

				if (field.getType().getName().contains("String")) {
					value = "'" + value + "'";
				}
				stBuilder.append(value).append(", ");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.err.println(e.getMessage());
			}
		}
		stBuilder.deleteCharAt(stBuilder.lastIndexOf(",")).append(" WHERE id = ");
		Field field = getIdField(entity);
		try {
			String query = stBuilder + field.get(entity).toString() + ";";
			exeUpdateQuery(query, null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.err.println(e.getMessage());
		}
	}

	public <T> T findById(Long id, Class<T> aClass) {
		if (aClass.isAnnotationPresent(OrmEntity.class)) {
			StringBuilder sb = new StringBuilder("SELECT * FROM ");
			sb.append(schema.toString())
					.append(".")
					.append(aClass.getAnnotation(OrmEntity.class).table())
					.append(" WHERE id=")
							.append(id);
			try {
				ResultSet set = dataSource.getConnection().createStatement().executeQuery(sb.toString());
				if (set.next()) {
					T t = aClass.newInstance();
					Field[] fields = aClass.getDeclaredFields();
					for (Field field : fields) {
						field.setAccessible(true);
						if (field.isAnnotationPresent(OrmColumnId.class)) {
							field.set(t, set.getLong("id"));
						}
						if (field.isAnnotationPresent(OrmColumn.class)) {
							OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);

							field.set(t, set.getObject(ormColumn.name()));
						}
						field.setAccessible(false);
					}
					return t;
				}
			} catch (SQLException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private Field getIdField(Object entity) {
		Field field;
		List<Field> fields = Arrays.stream(entity.getClass().getDeclaredFields()).map(f -> {
			if (f.getAnnotation(OrmColumnId.class) != null) {
				return f;
			}
			return null;
		}).filter(Objects::nonNull).collect(Collectors.toList());

		if (fields.isEmpty()) {
			throw new RuntimeException("Entity must have identifier");
		}
		field = fields.get(0);
		field.setAccessible(true);
		return field;
	}

	private List<Field> getColumnFields(Object entity) {
		List<Field> fields = Arrays.stream(entity.getClass().getDeclaredFields()).map(f -> {
			if (f.getAnnotation(OrmColumn.class) != null) {
				return f;
			}
			return null;
		}).filter(Objects::nonNull).collect(Collectors.toList());
		return fields;
	}
}