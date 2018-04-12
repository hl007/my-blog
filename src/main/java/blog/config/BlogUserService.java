package main.java.blog.config;
import main.java.blog.User;
import main.java.blog.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;


public class BlogUserService implements UserDetailsService {

    UserRepository userRepository;

    BlogUserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByName(username);

        if(!username.equals(user.getUsername())){
           throw new UsernameNotFoundException(username + " not found");
        }
        return new UserDetails() {

            private static final long serialVersionUID = 2059202961588104658L;

            @Override
            public boolean isEnabled() {
              return true;
          }

            @Override
            public boolean isCredentialsNonExpired() {
              return true;
          }

            @Override
            public boolean isAccountNonLocked() {
              return true;
          }

            @Override
            public boolean isAccountNonExpired() {
              return true;
          }

            @Override
            public String getUsername() {
              return username;
          }

            @Override
            public String getPassword() {
              return user.getPassword();
          }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                return auths;
            }
        };
    }

}
