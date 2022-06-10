
public class Main {

    public static void main(String[] args) {
        // in database i storing user : {login = newAndrew, password = password}
        Login login = new Login();
        System.out.println(login.verification("newAndrew", "password")); // true

        UserRepository ur = new UserRepository();
        ur.insertNewUser("admin", "admin");
    }
}



