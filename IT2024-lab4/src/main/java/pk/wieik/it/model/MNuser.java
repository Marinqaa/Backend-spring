package pk.wieik.it.model;

public class MNuser {
    private String login = "";
    private int privileges = -1;
    // -1 user not logged in
    // 1 user logged in
    // 2 administrator

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public int getPrivileges () {
        return privileges;
    }
    public void setPrivileges (int privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        return "DGuser{" +
                "login='" + login + '\'' +
                ", privileges=" + privileges +
                '}';
    }


}