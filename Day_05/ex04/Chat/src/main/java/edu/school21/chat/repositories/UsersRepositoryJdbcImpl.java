package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    private void readUserInfo(User user, ResultSet resultSet) throws SQLException {
        if (user.getListOfChatroomsWhereAUserSocializes() == null) {
            user.setListOfChatroomsWhereAUserSocializes(new ArrayList<>());
            Chatroom room = new Chatroom(resultSet.getInt(4), "-", null, null);
            if (!user.getListOfChatroomsWhereAUserSocializes().contains(room) && room.getChatroomName() != null) {
                user.getListOfChatroomsWhereAUserSocializes().add(room);
            }
        } else {
            Chatroom room = new Chatroom(resultSet.getInt(4), "-", null, null);
            if (!user.getListOfChatroomsWhereAUserSocializes().contains(room) && room.getChatroomName() != null) {
                user.getListOfChatroomsWhereAUserSocializes().add(room);
            }
        }
        if (user.getListCreatedRoom() == null) {
            user.setListCreatedRooms(new ArrayList<>());
            Chatroom room = new Chatroom(resultSet.getInt(6), resultSet.getString(7), null, null);
            if (!user.getListCreatedRoom() .contains(room) && room.getChatroomName() != null) {
                user.getListCreatedRoom().add(room);
            }
        } else {
            Chatroom room = new Chatroom(resultSet.getInt(6), resultSet.getString(7), null, null);
            if (!user.getListCreatedRoom().contains(room) && room.getChatroomName() != null) {
                user.getListCreatedRoom().add(room);
            }
        }
    }

    @Override
    public List<User> findAll(int page, int size) throws SQLException {
        Connection connection = ds.getConnection();

        Statement statement = connection.createStatement();
        String query = "SELECT users.id, users.login, users.password, m.room, m.text, c.id, c.name FROM users " +
                "FULL JOIN chatroom c on users.id = c.owner " +
                "FULL JOIN message m on users.id = m.author";
        ResultSet resultSet = statement.executeQuery(query);
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User newUser = new User(resultSet.getInt(1),
                    resultSet.getString("login"),
                    resultSet.getString("password"), null, null);
            if (users.contains(newUser)) {
                for (User user : users) {
                    if (user.equals(newUser)) {
                        readUserInfo(user, resultSet);
                        break;
                    }
                }
            } else {
                readUserInfo(newUser, resultSet);
                users.add(newUser);
            }
        }
        for (User user : users) {
            for (Chatroom room : user.getListCreatedRoom()) {
                if (!user.getListOfChatroomsWhereAUserSocializes().contains(room)) {
                    user.getListOfChatroomsWhereAUserSocializes().add(room);
                }
            }
        }
        int i = 0;
        for (int p = 0; p < page; p++, i += size) {}
        if (i >= users.size()) {
            System.err.println("Doesn't have page number - " + page);
        }
        List<User> result = new ArrayList<>(size);
        for (int j =0; j < size && i < users.size(); j++) {
            result.add(users.get(i));
            i++;
        }
        return result;
    }
}