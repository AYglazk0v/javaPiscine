public 	class UsersArrayList implements UserList {
	private static Integer lenghtArr = 10;
	private User[] users = new User[lenghtArr];
	private static Integer count = 0;

	@Override
	public void addUser(User newUser) {
		if (count < lenghtArr) {
			users[count] = newUser;
		}
		else {
			lenghtArr = lenghtArr + lenghtArr / 2;
			User[] newUsers = new User[lenghtArr];
			for (int i = 0; i < count; i ++) {
				newUsers[i] = users[i];
			}
			newUsers[count] = newUser;
			users = newUsers;
		}
		count++;
	}

	@Override
	public User getUserById(Integer id) {
		if (id < 0) {
			throw new UserNotFoundException("User not found");
		}
		else {
			for(int i = 0; i < count; i++) {
				if (users[i].getID() == id) {
					return (users[i]);
				}
			}
			throw new UserNotFoundException("User not found");
		}
	}

	@Override
	public User getUserByIndex(Integer index)
	{
		if (index < 0 || index > count){
			throw new UserNotFoundException("User not found");
		}
		return (users[index]);
	}

	@Override
	public Integer getUserCount() {
		return (count);
	}
}
