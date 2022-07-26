package edu.school21.chat.models;

import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

public class User {
	private Integer userId;
	private String login;
	private String password;
	private List<Chatroom> listCreatedRooms;
	private List<Chatroom> listOfChatroomsWhereAUserSocializes;
	
	public User(Integer id, String login, String passwd, List<Chatroom> listCreatedRooms, List<Chatroom> listOfChatroomsWhereAUserSocializes) {
		this.userId = id;
		this.login = login;
		this.password = passwd;
		this.listCreatedRooms = listCreatedRooms;
		this.listOfChatroomsWhereAUserSocializes = listOfChatroomsWhereAUserSocializes;
	}

	public Integer getUserId() {
		return (userId);
	}

	public String getLogin() {
		return (login);
	}

	public String getPasswd() {
		return (password);
	}

	public List<Chatroom> getListCreatedRoom() {
		return (listCreatedRooms);
	}

	public List<Chatroom> getListOfChatroomsWhereAUserSocializes() {
		return (listOfChatroomsWhereAUserSocializes);
	}

	public void setUserId(Integer id) {
		this.userId = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPasswd(String passwd) {
		this.password = passwd;
	}

	public void setListOfChatroomsWhereAUserSocializes(List<Chatroom> lst) {
		this.listOfChatroomsWhereAUserSocializes = lst;
	}

	public void setListCreatedRooms(List<Chatroom> lst) {
		this.listCreatedRooms = lst;
	}

	@Override
	public int hasCode() {return super.hashCode();}
	
	@Override
	public boolean equals(obj e) {return super.equals(e);}

	@Override
	public String toString() {
		return ("User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", createdRooms=" + createdRooms +
				", userRooms=" + userRooms +
				'}');
	}
}
