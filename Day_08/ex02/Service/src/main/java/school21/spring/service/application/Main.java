package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UsersService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("school21.spring.service");
		UsersService usersService = context.getBean("usersService", UsersService.class);
		UsersRepository usersJdbcTemplate = context.getBean("jdbcTemplateRepository", UsersRepository.class);
		UsersRepository usersJdbc = context.getBean("jdbcRepository", UsersRepository.class);
		createTable(context);

		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersService.signUp("adsfkjhds@mail.ru"));
		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersService.signUp("ad6056ds@mail.ru"));
		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersService.signUp("adsfkjhdadsfasdfs@mail.ru"));
		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersService.signUp("akjldjsfiaousr@mail.ru"));
		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersService.signUp("8956546578654@mail.ru"));

		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersJdbcTemplate.findAll());
		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersJdbc.findAll());
		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersJdbcTemplate.findById(1L));
		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersJdbc.findById(1L));
		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersJdbcTemplate.findByEmail("akjldjsfiaousr@mail.ru"));
		System.out.println("\n___________________________________________________________________________________");
		System.out.println(usersJdbc.findByEmail("akjldjsfiaousr@mail.ru"));
		System.out.println("\n___________________________________________________________________________________");
	}

	private static void createTable(ApplicationContext context) {
		DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
		try (Connection con = dataSource.getConnection();
			Statement st = con.createStatement()) {
			st.executeUpdate("drop schema if exists models cascade;");
			st.executeUpdate("create schema if not exists models;");
			st.executeUpdate("create table if not exists models.user "
					+ "(id integer, email varchar(50) not null, password varchar(50));");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
