package s24.ruokasovelluss.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import s24.ruokasovelluss.domain.AppUser;
import s24.ruokasovelluss.domain.AppUserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
	AppUserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
        AppUser curruser = repository.findByUsername(username);
        if (curruser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new CustomUserDetails(curruser);
    }
}