public class Program{
		public static void main(String arg[]) {
		{
			User u1 = new User(502, "Ivan");
			User u2 = new User(504, "Ivan");
			User u3 = new User(506, "Ivan");
			User u4 = new User(508, "Ivan");
			User u5 = new User(510, "Ivan");
			User u6 = new User(512, "Ivan");
			User u7 = new User(514, "Ivan");
			User u8 = new User(516, "Ivan");
			User u9 = new User(518, "Ivan");
			User u10 = new User(510, "Ivan");
			User u11 = new User(511, "Ivan");
			User u12 = new User(512, "Ivan");
			User u13 = new User(513, "Ivan");
			User u14 = new User(514, "Ivan");
			User u15 = new User(515, "Ivan");
			User u16 = new User(516, "Ivan");
			User u17 = new User(517, "Ivan");
			User u18 = new User(518, "Ivan");
			User u19 = new User(519, "Ivan");
			User u20 = new User(520, "Ivan");
			User u21 = new User(521, "Ivan");
			User u22 = new User(522, "Ivan");
			User u23 = new User(523, "Ivan");

			UsersArrayList userList = new UsersArrayList();
			userList.addUser(u1);
			userList.addUser(u2);
			userList.addUser(u14);
			userList.addUser(u15);
			userList.addUser(u3);
			userList.addUser(u4);
			userList.addUser(u21);
			userList.addUser(u22);
			userList.addUser(u5);
			userList.addUser(u6);
			userList.addUser(u9);
			userList.addUser(u10);
			userList.addUser(u11);
			userList.addUser(u12);
			userList.addUser(u13);
			userList.addUser(u7);
			userList.addUser(u18);
			userList.addUser(u19);
			userList.addUser(u8);
			userList.addUser(u17);
			userList.addUser(u16);
			userList.addUser(u20);
			userList.addUser(u23);

			System.out.println("count: " + userList.getUserCount());
			System.out.println("User with id 6: " + userList.getUserById(6));
			System.out.println("User with index 6: " + userList.getUserByIndex(6));
			try {
			System.out.println("Test exeption: " + userList.getUserById(50));
			System.out.println("Test exeption: " + userList.getUserById(-50));
			System.out.println("Test exeption: " + userList.getUserByIndex(-50));
			System.out.println("Test exeption: " + userList.getUserByIndex(50));
			}
			catch(UserNotFoundException err){
				System.out.println(err.getMessage());
			}	
		}
	}
}
