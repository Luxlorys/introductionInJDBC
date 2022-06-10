public class User {

    private final int id;
    private final String login;
    private final String password;
    private final String salt;

    public User(int id, String login, String password, String salt) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }


    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }


    public String getSalt() {
        return salt;
    }


}
