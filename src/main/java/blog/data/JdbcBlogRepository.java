package main.java.blog.data;

import main.java.blog.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcBlogRepository implements BlogRepository{

    @Autowired
    private JdbcOperations jdbc;

    @Override
    public void save(Blog blog){
        jdbc.update(
                "insert into blog (name, title, summary, content, tag1, tag2,tag3, tag4, date, pathname)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                blog.getName(),
                blog.getTitle(),
                blog.getSummary(),
                blog.getContent(),
                blog.getTag1(),blog.getTag2(),blog.getTag3(),blog.getTag4(),
                blog.getDate(),
                blog.getPathname());
    }

    @Override
    public List<Blog> findBlogs(int batch, int count){
        return jdbc.query(
                "select title, summary, name, date, pathname,id from blog " +
                        "order by id desc limit ?,?" ,
                new BlogRowMapper(),(batch-1)*count,batch*count);
    }

    @Override
    public  int countBlogs(){
        List<count> countList=jdbc.query(
                "select count(*) from blog ",
                new RowMapper<count>() {
                    @Override
                    public count mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new count(
                                rs.getInt("count(*)"));
                    }
                });
         return countList.get(0).getCount();
    }

    @Override
    public List<Blog> findRecentBlogs(){
        return jdbc.query(
                "select title,id from blog order by id desc limit 0,4",
                new RowMapper<Blog>() {
                    @Override
                    public Blog mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Blog(
                                resultSet.getString("title"),
                                resultSet.getString("id"));
                    }
                });
    }

    @Override
    public Blog findById(String id){
         List<Blog> blogList=jdbc.query(
                "select * from blog where id=?",
                new RowMapper<Blog>() {
                    @Override
                    public Blog mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Blog(
                                resultSet.getString("content"),
                                resultSet.getString("title"),
                                resultSet.getString("summary"),
                                resultSet.getString("name"),
                                resultSet.getString("date"),
                                resultSet.getString("pathname"),
                                resultSet.getString("id"),
                                resultSet.getString("tag1"),
                                resultSet.getString("tag2"),
                                resultSet.getString("tag3"),
                                resultSet.getString("tag4")
                        );
                    }
                },id);
         return blogList.get(0);
    };

    @Override
    public List<Blog> findRelatedBlogs(Blog blog){
        List<Blog> blogList=jdbc.query(
                "select title,id,pathname from blog where tag1=? order by id desc limit 0,4",
                new RowMapper<Blog>() {
                    @Override
                    public Blog mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Blog(
                                resultSet.getString("title"),
                                resultSet.getString("id"),
                                resultSet.getString("pathname"));
                    }
                },blog.getTag1());

        Iterator<Blog> iterator=blogList.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getId().equals(blog.id)) {
                iterator.remove();
            }
        }

        while(blogList.size()>=4){
            iterator.remove();
        }

        return blogList;
    }

    @Override
    public Set<String> findTags(){
        List<Blog> blogList=jdbc.query(
                "select tag1,tag2,tag3,tag4 from blog",
                new RowMapper<Blog>() {
                    @Override
                    public Blog mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Blog(
                                resultSet.getString("tag1"),
                                resultSet.getString("tag2"),
                                resultSet.getString("tag3"),
                                resultSet.getString("tag4"));
                    }
                });
        Iterator<Blog> iterator=blogList.iterator();
        Set<String> tagSet=new HashSet<>();

        while (iterator.hasNext()) {
            Blog blog = iterator.next();

            if(blog.tag1.length()!=0) {
                tagSet.add(blog.tag1);
            }
            if(blog.tag2.length()!=0) {
                tagSet.add(blog.tag2);
            }
            if(blog.tag3.length()!=0) {
                tagSet.add(blog.tag3);
            }
            if(blog.tag4.length()!=0) {
                tagSet.add(blog.tag4);
            }
        }

        return tagSet;
    }

    @Override
    public List<Blog> findByTag(String tag){
        return jdbc.query(
                "select title, summary, name, date, pathname,id from blog where tag1=? OR tag2=? OR tag3=? OR tag4=?",
                new BlogRowMapper(),tag,tag,tag,tag);
    }

    private static class count{
        int count;

        public count(int count){
            setCount(count);
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }

    private static class BlogRowMapper implements RowMapper<Blog> {

        //返回Blog对象
        @Override
        public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Blog(
                    rs.getString("title"),
                    rs.getString("summary"),
                    rs.getString("name"),
                    rs.getString("date"),
                    rs.getString("pathname"),
                    rs.getString("id")
            );
        }
    }
}
