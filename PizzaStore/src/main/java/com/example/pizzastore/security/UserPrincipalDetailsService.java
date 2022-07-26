package com.example.pizzastore.security;


import com.example.pizzastore.entity.User;
import com.example.pizzastore.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The Class UserPrincipalDetailsService.
 */
@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    
    /** The user repository. */
    private UserRepository userRepository;

    /**
     * Instantiates a new user principal details service.
     *
     * @param userRepository the user repository
     */
    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByName(s);
        if(user == null) {
        	throw new UsernameNotFoundException(s);
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}