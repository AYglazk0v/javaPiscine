package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        createTable(context);
        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

        User user1 = new User(1L,"qwertyuiop@mail.ru");
        User user2 = new User(2L,"asdfghj@mail.ru");
        User user3 = new User(3L,"963258741@mail.ru");
        User user4 = new User(4L,"0123456789@mail.ru");

		System.out.println("_____________________________________________________________________________________________________________");
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(user3);
		System.out.println(user4);
		System.out.println("_____________________________________________________________________________________________________________");


        usersRepositoryJdbc.save(user1);
        usersRepositoryJdbc.save(user2);
        usersRepositoryJdbc.save(user3);
        usersRepositoryJdbc.save(user4);

        System.out.println(usersRepositoryJdbc.findAll());
		System.out.println("_____________________________________________________________________________________________________________");
        System.out.println(usersRepositoryJdbcTemplate.findAll());
		System.out.println("_____________________________________________________________________________________________________________");

        User user5 = new User(5L, "ffhasfdtygshvaf@mail.ru");
        User user6 = new User(6L, "oiuhgvbnmhghvbn@mail.ru");
        User user7 = new User(7L, "ijuhgjbnjkuiytfghvbh@mail.ru");
        User user8 = new User(8L, "iuytghvbjuytfhgcvbhtfgd@mail.ru");
        User user9 = new User(9L, "jhgjftghjiuyutfgcvb@mail.ru");
        User user10 = new User(10L, "uyitfghjuiytfgcgvhjuygfhc@mail.ru");
//
		usersRepositoryJdbcTemplate.save(user5);
		usersRepositoryJdbcTemplate.save(user6);
		usersRepositoryJdbcTemplate.save(user7);
		usersRepositoryJdbcTemplate.save(user8);
		usersRepositoryJdbcTemplate.save(user9);
		usersRepositoryJdbcTemplate.save(user10);
		System.out.println("_____________________________________________________________________________________________________________");
        System.out.println(usersRepositoryJdbc.findAll());
		System.out.println("_____________________________________________________________________________________________________________");
        System.out.println(usersRepositoryJdbcTemplate.findAll());
		System.out.println("_____________________________________________________________________________________________________________");

        usersRepositoryJdbc.delete(10L);
        usersRepositoryJdbcTemplate.delete(9L);

        user5.setEmail("UPD");
        user6.setEmail("UPD");
        usersRepositoryJdbc.update(user5);
        usersRepositoryJdbcTemplate.update(user6);

        System.out.println(usersRepositoryJdbc.findAll());
		System.out.println("_____________________________________________________________________________________________________________");
        System.out.println(usersRepositoryJdbcTemplate.findAll());
		System.out.println("_____________________________________________________________________________________________________________");
        System.out.println(usersRepositoryJdbc.findById(1l));
		System.out.println("_____________________________________________________________________________________________________________");
        System.out.println(usersRepositoryJdbcTemplate.findById(2l));
		System.out.println("_____________________________________________________________________________________________________________");

        System.out.println(usersRepositoryJdbc.findByEmail("0123456789@mail.ru"));
		System.out.println("_____________________________________________________________________________________________________________");
        System.out.println(usersRepositoryJdbcTemplate.findByEmail("0123456789@mail.ru"));
		System.out.println("_____________________________________________________________________________________________________________");
        ((ClassPathXmlApplicationContext) context).close();
    }

    private static void createTable(ApplicationContext context) {
        DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("drop schema if exists models cascade;");
            st.executeUpdate("create schema if not exists models;");
            st.executeUpdate("create table if not exists models.user "
                    + "(id integer not null, email varchar(50) not null);");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}