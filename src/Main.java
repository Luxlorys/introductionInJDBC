import repository.UserRepository;
import services.DAO;
import services.Login;
import services.MysqlDAO;

public class Main {

    public static void main(String[] args) {
        // in database i storing user : {login = newAndrew, password = password}

        Login login = new Login();
        DAO mysql = new MysqlDAO();

        UserRepository userRepository = new UserRepository(mysql);


        // you can select all users, delete or insert user, update login or password;
        // id db store entity.User with login = 'gromozeka' and password = 'gromozeqa'
//        System.out.println(login.verification("gromozeka", "gromozeka")); // output: true
//        userRepository.changePassword("gromozeka", "qwerty");

//        userRepository.insertNewUser("root", "root");

//        userRepository.insertNewUser("root", "root");
//        System.out.println(userRepository.deleteUser("user2"));
//        userRepository.insertNewUser("root12", "root5");
        userRepository.getAllUsers();
        System.out.println(login.verification("root12", "root5"));
        System.out.println(userRepository.deleteUser("root"));

    }
}



