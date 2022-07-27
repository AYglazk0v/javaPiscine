package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
	private final DataSource dataSource;

	public ProductsRepositoryJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Product> findAll() {
		List<Product> productList = new ArrayList<>();

		String allQuery = "SELECT * FROM product";
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(allQuery)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product(resultSet.getLong(1),
											resultSet.getString(2),
											resultSet.getDouble(3));
											productList.add(product);
									}
		} catch (SQLException e) {
			System.out.println(e.getMessage());}
			return productList;
	}

	@Override
	public Optional<Product> findById(Long id) {
		String idQuery = "SELECT * FROM product WHERE id = ";

		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(idQuery + id)) {
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				return Optional.empty();
			}
			Product product = new Product(resultSet.getLong(1),
										resultSet.getString(2),
										resultSet.getDouble(3));
			return Optional.of(product);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public void update(Product product) {
		String updateQuery = "UPDATE product SET name = ?, price = ? WHERE id = ?";
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(updateQuery)) {
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setLong(3, product.getId());
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void save(Product product) {
		String saveQuery = "INSERT INTO product(name, price) VALUES (?, ?)";
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(saveQuery)) {
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) {
		String deletelQuery = "DELETE FROM product WHERE id = ";
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(deletelQuery + id)) {
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}