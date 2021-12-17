package model;

/**
 * Users defines the appointment information - getters/setters/list/etc.
 */
public class Users {
    private int userId;
    private static String userName;
    private String password;

    /**
     *
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return userName
     */
    public static String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     * @return
     */
    public String setUserName(String userName) {
        Users.userName = userName;
        return userName;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     *
     * @param userId
     * @param userName
     * @param password
     */
    public Users(int userId, String userName, String password) {
        this.userId = userId;
        Users.userName = userName;
        this.password = password;
    }

    /**
     *
     * @param username
     * @param userPassword
     * @return if username is valid or not
     */
    public boolean nameLoginGood(String username, String userPassword)
    {
        return username.equals(userName) && userPassword.equals(password);
    }

}
