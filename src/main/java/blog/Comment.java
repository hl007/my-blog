package main.java.blog;


import java.util.List;

public class Comment {
    public String c_id;
    public String id;
    public String c_username;
    public String c_avatar;
    public String c_time;
    public String c_message;
    public List<Reply> replyList;

    public Comment(){}

    public Comment(List<Reply> replyList,String id,String c_id, String c_username, String c_avatar, String c_time, String c_message){
        this.replyList=replyList;
        this.id=id;
        this.c_id=c_id;
        this.c_username=c_username;
        this.c_avatar=c_avatar;
        this.c_time=c_time;
        this.c_message=c_message;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public void setC_avatar(String c_avatar) {
        this.c_avatar = c_avatar;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    public void setC_message(String c_message) {
        this.c_message = c_message;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public String getC_id() {
        return c_id;
    }

    public String getId() {
        return id;
    }

    public String getC_username() {
        return c_username;
    }

    public String getC_avatar() {
        return c_avatar;
    }

    public String getC_time() {
        return c_time;
    }

    public String getC_message() {
        return c_message;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }
}
