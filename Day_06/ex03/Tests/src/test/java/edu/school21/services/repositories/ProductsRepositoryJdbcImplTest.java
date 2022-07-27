package edu.school21.services.repositories;

import edu.school21.models.Product;
import edu.school21.repositories.ProductsRepository;
import edu.school21.repositories.ProductsRepositoryJdbcImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductsRepositoryJdbcImplTest {
	private ProductsRepository repository;
	private DataSource dataSource;

	final List<Product> FIND_ALL = Arrays.asList(
		new Product(1l, "a", 57.20),
		new Product(2l, "b", 242.20),
		new Product(3l, "c", 130.20),
		new Product(4l, "d", 131.22),
		new Product(5l, "e", 629.17),
		new Product(6l, "f", 478.14));

		final Product FIND_BY_ID = new Product(2l, "b", 242.20);

		final Product UPDATED_PRODUCT = new Product(4l, "0xffffff", 255255255.0);

		final Product SAVE_PRODUCT = new Product(7l, "0x00ffff", 255255.0);

		final List<Product> PRODUCTS_AFTER_DELETE = Arrays.asList(
							new Product(2l, "b", 242.20),
							new Product(3l, "c", 130.20),
							new Product(4l, "d", 131.22),
							new Product(5l, "e", 629.17),
							new Product(6l, "f", 478.14));

		@BeforeEach
		private void init() {
			dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
			.addScript("schema.sql")
			.addScript("data.sql")
			.build();
			repository = new ProductsRepositoryJdbcImpl(dataSource);
		}

		@Test
		public void findAllTest() {
			assertEquals(FIND_ALL, repository.findAll());
		}

		@Test
		public void findByIdTest() throws SQLException {
			assertEquals(FIND_BY_ID, repository.findById(2l).get());
			assertEquals(Optional.empty(), repository.findById(10l));
			assertEquals(Optional.empty(), repository.findById(null));
		}

		@Test
		public void updateTest() throws SQLException {
			repository.update(new Product(4l, "0xffffff", 255255255.0));
			assertEquals(UPDATED_PRODUCT, repository.findById(4l).get());
		}

		@Test
		public void saveTest() throws SQLException {
			repository.save(new Product(7l, "0x00ffff", 255255.0));
			assertEquals(SAVE_PRODUCT, repository.findById(7l).get());
		}
	
		@Test
		public void deleteTest() throws SQLException {
			repository.delete(1l);
			assertEquals(PRODUCTS_AFTER_DELETE, repository.findAll());
			assertFalse(repository.findById(1l).isPresent());
		}
}