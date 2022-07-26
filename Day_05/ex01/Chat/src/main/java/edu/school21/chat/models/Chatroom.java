package edu.school21.chat.models;

import java.security.cert.PKIXCertPathValidatorResult;
import java.util.List;

public class Chatroom {
	private Integer chatroomId;
	private String chatroomName;
	private User chatroomOwner;
	List<Message> messagesList;

	public Chatroom(Integer chatroomId, String name, User owner, List<Message> messageList) {
		this.chatroomId = chatroomId;
		this.chatroomName = name;
		this.chatroomOwner = owner;
		this.messagesList = messageList;
	}

	public Integer getChatroomId() {
		return (chatroomId);
	}

	public String getChatroomName() {
		return (chatroomName);
	}

	public User getChatroomOwner() {
		return (chatroomOwner);
	}

	public List<Message> getMesagesLst() {
		return (messagesList);
	}

	public void setChatroomId(Integer id) {
		this.chatroomId = id;
	}

	public void setChatroomName(String name) {
		this.chatroomName = name;
	}

	public void setNessagesLst(List<Message> lst) {
		this.messagesList = lst;
	}
	
	@Override
	public int hashCode() {return super.hashCode();}
	
	@Override
	public boolean equals(Object e) {return super.equals(e);}

	@Override
	public String toString() {
		return ("Chatroom{" +
				"id=" + chatroomId +
				", name='" + chatroomName + '\'' +
				", owner=" + chatroomOwner +
				", roomMessages=" + messagesList +
				'}');
	}
}
