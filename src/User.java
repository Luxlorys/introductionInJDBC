public class User {

    private int id;
    private String login;
    private String password;
    private String salt;

    public User(int id, String login, String password, String salt) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
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
