package edu.lk.ijse.farm.dto;

public class UserDto {
    private int id;
    private String UserName;
    private String Password;
    private String UserEmail;

    public UserDto(){

    }
    public UserDto(int id, String UserName, String Password, String UserEmail) {
        this.id = id;
        this.UserName = UserName;
        this.Password = Password;
        this.UserEmail = UserEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", UserEmail='" + UserEmail + '\'' +
                '}';
    }
}
