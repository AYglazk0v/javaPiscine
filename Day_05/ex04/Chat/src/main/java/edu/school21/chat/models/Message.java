package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	private Integer messageId;
	private User messageAuthor;
	private Chatroom room;
	private String text;
	private LocalDateTime date;

	public Message(Integer id, User author, Chatroom room, String text, LocalDateTime date){
		this.messageId = id;
		this.messageAuthor = author;
		this.room = room;
		this.text = text;
		this.date = date;
	}

	public Integer getMessageId(){
		return (messageId);
	}

	public User getAuthorUser(){
		return (messageAuthor);
	}

	public Chatroom getChatroom(){
		return (room);
	}

	public String getText(){
		return (text);
	}

	public LocalDateTime getDate(){
		return (date);
	}

	public void setMessageId(Integer messageId){
		this.messageId = messageId;
	}

	public void setAuthorUser(User messageAuthor){
		this.messageAuthor = messageAuthor;
	}

	public void setChatroom(Chatroom room){
		this.room = room;
	}

	public void setText(String text) {
		if (text == null) {
			this.text = "-";
		} else {
			this.text = text;
		}
	}

	public void setDate(LocalDateTime date) {
		if (date == null) {
			this.date = LocalDateTime.parse("0000-00-00 00:00:00",
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		} else {
			this.date = date;
		}
	}
		
	@Override
	public int hashCode() {return super.hashCode();}
	
	@Override
	public boolean equals(Object e) {return super.equals(e);}

	@Override
	public String toString(){
		return ("Message{" +
				"id="+ messageId +
				", author=" + messageAuthor +
				", chatroom=" + room +
				", text=" + text + '\'' +
				", messageDateTime=" + date +
				'}');
	}
}