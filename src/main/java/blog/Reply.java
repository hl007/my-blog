package main.java.blog;

public class Reply {
    public String r_id;
    public String c_id;
    public String r_username;
    public String r_avatar;
    public String r_time;
    public String r_message;

    public Reply(){}

    public Reply(String c_id,String r_id, String r_username, String r_avatar, String r_time, String r_message){
        this.c_id=c_id;
        this.r_id=r_id;
        this.r_username=r_username;
        this.r_avatar=r_avatar;
        this.r_time=r_time;
        this.r_message=r_message;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public void setR_username(String r_username) {
        this.r_username = r_username;
    }

    public void setR_avatar(String r_avatar) {
        this.r_avatar = r_avatar;
    }

    public void setR_time(String r_time) {
        this.r_time = r_time;
    }

    public void setR_message(String r_message) {
        this.r_message = r_message;
    }

    public String getC_id() {
        return c_id;
    }

    public String getR_id() {
        return r_id;
    }

    public String getR_username() {
        return r_username;
    }

    public String getR_avatar() {
        return r_avatar;
    }

    public String getR_time() {
        return r_time;
    }

    public String getR_message() {
        return r_message;
    }
}


