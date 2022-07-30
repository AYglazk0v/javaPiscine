package school21.spring.service.repositories;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import school21.spring.service.models.User;

public class UsersRepositoryJdbcImpl implements UsersRepository {
	private DataSource dataSource;

	public UsersRepositoryJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public User findById(Long id) {
		try (Connection connect = dataSource.getConnection();Statement statement = connect.createStatement()) {
			ResultSet returnStatement = statement.executeQuery("SELECT * FROM models.user WHERE id = " + id);
			if (!returnStatement.next()) {
				return null;
			}
			return new User(returnStatement.getLong(1), returnStatement.getString(2));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try (Connection connect = dataSource.getConnection(); Statement statement = connect.createStatement()) {
			ResultSet returnStatement = statement.executeQuery("SELECT * FROM models.user");
			while (returnStatement.next()) {
				users.add(new User(returnStatement.getLong(1), returnStatement.getString(2)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return users.isEmpty() ? null : users;
	}

	@Override
	public void save(User entity) {
		try (Connection connect = dataSource.getConnection(); Statement statement = connect.createStatement()) {
			int result = statement.executeUpdate("INSERT INTO models.user (id, email) VALUES ("
					+ entity.getIdentifier() + ", '"
					+ entity.getEmail() + "');");
			if (result == 0) {
				System.err.println("ERROR: USER NOT SAVED");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void update(User entity) {
		try (Connection connect = dataSource.getConnection(); PreparedStatement statement = connect.prepareStatement("UPDATE models.user SET email = ? WHERE id = ?")) {
			statement.setString(1, entity.getEmail());
			statement.setLong(2, entity.getIdentifier());
			int result = statement.executeUpdate();
			if (result == 0) {
				System.err.println("EROR: INFORM NOT UPDATED");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) {
		try (Connection connect = dataSource.getConnection(); PreparedStatement statement = connect.prepareStatement("DELETE FROM models.user WHERE id = " + id)) {
			int result = statement.executeUpdate();
			if (result == 0) {
				System.err.println("USER NOT FOUND");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public Optional<User> findByEmail(String email) {
		try (Connection connect = dataSource.getConnection(); PreparedStatement statement = connect.prepareStatement("SELECT * FROM models.user WHERE email = ?")) {
			statement.setString(1, email);
			ResultSet returnStatement = statement.executeQuery();
			if (!returnStatement.next()) {
				return Optional.empty();
			}
			return Optional.of(new User(returnStatement.getLong(1), returnStatement.getString(2)));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return Optional.empty();
	}
}
