package main.java.blog;

public class SubscribeEmail {
    public String id;
    public String email;
    public String time;

    public SubscribeEmail(){};

    public SubscribeEmail(String email){
        this.email=email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTime() {
        return time;
    }
}
