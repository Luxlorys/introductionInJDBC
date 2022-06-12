
public class Main {

    public static void main(String[] args) {
        // in database i storing user : {login = newAndrew, password = password}

        Login login = new Login();
        UserRepository userRepository = new UserRepository();


        // you can select all users, delete or insert user, update login or password;
        // id db store User with login = 'gromozeka' and password = 'gromozeqa'
        System.out.println(login.verification("gromozeka", "gromozeka")); // output: true


    }
}



