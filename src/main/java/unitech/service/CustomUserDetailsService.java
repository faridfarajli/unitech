package unitech.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import unitech.model.User;
import unitech.repo.UserRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByFin(username);
        var roles = Stream.of(user.getRole())
                .map(x -> new SimpleGrantedAuthority(x.name()))
                .collect(Collectors.toList());
        if (user != null){
            return new org.springframework.security.core.userdetails.User(user.getFin(),user.getPassword(),roles);

        }else {
            throw new UsernameNotFoundException("Şifrə və ya email səhvdir.");
        }
    }
}
