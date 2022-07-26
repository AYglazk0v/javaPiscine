package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import com.zaxxer.hikari.HikariDataSource;

public class Program {
	private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static String USER = "postgres";
	private static String PASS = "qwerty";
	public static void main(String[] args) throws SQLException {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(DB_URL);
		ds.setUsername(USER);
		ds.setPassword(PASS);
	MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
		Optional<Message> messageOptional = messagesRepository.findById((long) 1);
		if (messageOptional.isPresent()) {
			Message message = messageOptional.get();
			message.setText("How are you!");
			message.setDate(LocalDateTime.now());
			messagesRepository.update(message);
		}
	}
}
