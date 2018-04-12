package main.java.blog.data;

import main.java.blog.Blog;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Set;

public interface BlogRepository {

    //保存blog
    void save(Blog blog);

    //批量查询blog
    List<Blog> findBlogs(int batch, int count);

    //查询blog数目
    int countBlogs();

    //查询最近blog
    List<Blog> findRecentBlogs();

    //查询指定blog
    //添加缓存
    @Cacheable(value = "blog",key = "#id")
    Blog findById(String id);

    //查询指定blog的相关blog
    List<Blog> findRelatedBlogs(Blog blog);

    //查询tags
    Set<String> findTags();

    //查训指定tag的blog
    List<Blog> findByTag(String tag);
}
