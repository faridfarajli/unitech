package unitech.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unitech.dto.UserDto;
import unitech.model.User;
import unitech.repo.UserRepository;

import static unitech.util.ErrorMessage.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Object register(UserDto userDto) throws Exception {
        User user = new User();
        user.setName(userDto.name());
        user.setSurname(userDto.surname());
        user.setFin(userDto.fin());
        user.setPhoneNumber(userDto.phoneNumber());
        user.setRole(userDto.role());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        if (userRepository.existsByFin(user.getFin())) {
          return TAKEN_FIN;
        }
        if (user.getFin().length() != 7) {
            return INVALID_FIN;
        }
        userRepository.save(user);
        return user;
    }

    public Object login (UserDto userDto) throws Exception{
        User user = userRepository.findByFin(userDto.fin());
        if (user == null  || !passwordEncoder.matches(userDto.password(), user.getPassword())){
            return WRONG_USER_DETAIL;
        }
        return user;
    }


}
