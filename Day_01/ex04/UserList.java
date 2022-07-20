interface UserList {
    void addUser(User newUser);
    User getUserById(Integer id) throws UserNotFoundException;
    User getUserByIndex(Integer index) throws UserNotFoundException;
    Integer getUserCount();
}