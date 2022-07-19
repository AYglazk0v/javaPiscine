public class UserIdsGenerator {

		private static UserIdsGenerator INSTANCE;
		private static Integer UID = 0;
		
		public static UserIdsGenerator getInstance() {
			if(INSTANCE == null) {
				INSTANCE = new UserIdsGenerator();
			}
			return (INSTANCE);
		}

		public int generateId() {
			return(++UID);
		}
}
