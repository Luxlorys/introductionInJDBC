public class Main {

    public static void main(String[] args) {
        UserRepository getUser = new UserRepository();

        System.out.println(getUser.getUserById(2).getLogin());
    }
}



