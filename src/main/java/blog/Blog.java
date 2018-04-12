package main.java.blog;


public class Blog {
    public String content;
    public String title;
    public String summary;
    public String tag1;
    public String tag2;
    public String tag3;
    public String tag4;
    public String date;
    public String name;
    public String pathname;
    public String id;

    public Blog(){
    }

    public Blog(String title,String id){
        this.title=title;
        this.id=id;
    }

    public Blog(String title,String id,String pathname){
        this.title=title;
        this.id=id;
        this.pathname=pathname;
    }

    public Blog(String content,String title,String summary,String name,String date,String pathname,
                String id,String tag1,String tag2,String tag3,String tag4){
        this.content=content;
        this.title=title;
        this.summary=summary;
        this.name=name;
        this.date=date;
        this.pathname=pathname;
        this.id=id;
        this.tag1=tag1;
        this.tag2=tag2;
        this.tag3=tag3;
        this.tag4=tag4;
    }

    public Blog(String title,String summary,String name,String date,String pathname,
                String id){
        this.title=title;
        this.summary=summary;
        this.name=name;
        this.date=date;
        this.pathname=pathname;
        this.id=id;
    }

    public Blog(String tag1,String tag2,String tag3,String tag4){
        this.tag1=tag1;
        this.tag2=tag2;
        this.tag3=tag3;
        this.tag4=tag4;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getTag1() {
        return tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public String getTag4() {
        return tag4;
    }

    public String getSummary() {
        return summary;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getPathname() {
        return pathname;
    }

    public String getId() {
        return id;
    }
}
