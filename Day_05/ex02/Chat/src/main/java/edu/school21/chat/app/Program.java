package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.models.User;

import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.NotSavedSubEntityException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.zaxxer.hikari.HikariDataSource;

public class Program {
	private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static String USER = "gtaggana";
	private static String PASS = "qwerty";
	public static void main(String[] args) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(DB_URL);
		ds.setUsername(USER);
		ds.setPassword(PASS);

		User creator = new User(1, "user", "user", new ArrayList(), new ArrayList());
		User author = creator;
		Chatroom room = new Chatroom(1, "room", creator, new ArrayList());
		Message message = new Message(null, author, room, "Welcome to the club buddy!", LocalDateTime.now());
		MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);

		try {
			messagesRepository.save(message);
		} catch (NotSavedSubEntityException | SQLException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		System.out.println(message.getMessageId());
	}
}
