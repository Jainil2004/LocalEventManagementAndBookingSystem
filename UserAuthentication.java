// the purpose of this class is to register users in the system.
// In future more methods will be added to implement better user authentication
// like multi-user capabilities, better security etc
// version v1.0.0-alpha5 has just basic structure
public class UserAuthentication {
    static {
        System.out.println("\n**UserAuthentication active and working**\n");
    }

    public static boolean registerUser(String userName, String password, String email, User Users[]) {
        boolean registered = false;

        for (int i = 0; i < Users.length; i++) {
            if (Users[i] != null && Users[i].getuserName().equals(userName)) {
                return false; // User already exists, return false
            } else if (Users[i] == null) {
                Users[i] = new NormalUser(userName, password, email);
                registered = true;
                break; // Registration successful, exit the loop
            }
        }
        return registered; // Return true if registered, false if not
    }
}