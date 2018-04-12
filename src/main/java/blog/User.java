package main.java.blog;

public class User {
    public String id;
    public String email;
    public String username;
    public String password;
    public String avatar;

    public User(){}

    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    public User(String username,String avatar,String id){
        this.username=username;
        this.avatar=avatar;
        this.id=id;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }
}
