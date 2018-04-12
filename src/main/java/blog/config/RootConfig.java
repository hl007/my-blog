package main.java.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "main.java.blog.config")
public class RootConfig {
    public static class WebPackage  {
    }
}
