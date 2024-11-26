package s24.ruokasovelluss.web;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import s24.ruokasovelluss.domain.UserR;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserR userRepository;

    public CustomUserDetailsService(UserR userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        s24.ruokasovelluss.domain.User curruser = userRepository.findByUsername(username);
        if (curruser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User.builder()
                .username(curruser.getUsername())
                .password(curruser.getPasswordHash()) // Ensure this matches the password hash in your database
                .roles(curruser.getRole())           // Assuming your `User` entity has a `role` field
                .build();
    }
}
