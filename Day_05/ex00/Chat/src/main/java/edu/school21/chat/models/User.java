package edu.school21.chat.models;

import java.util.List;

public class User {
	private static Integer userId;
	private static String login;
	private static String password;
	private static List<Chatroom> listCreatedRooms;
	private static List<Chatroom> listOfChatroomsWhereAUserSocializes;
	
	public User(Integer id, String login, String passwd, List<Chatroom> listCreatedRooms, List<Chatroom> listOfChatroomsWhereAUserSocializes) {
		this.userId = id;
		this.login = login;
		this.password = passwd;
		this.listCreatedRooms = listCreatedRooms;
		this.listOfChatroomsWhereAUserSocializes = listOfChatroomsWhereAUserSocializes;
	}
	
	@Override
	public int hasCode() {return super.hashCode();}
	
	@Override
	public int equals(obj e) {return super.equals(e);}
	
}
