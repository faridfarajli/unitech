package unitech.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unitech.dto.CCDto;
import unitech.dto.UserDto;
import unitech.service.CCService;
import unitech.service.UserService;

@RestController
@RequestMapping
public class UserController {
    private final UserService service;



    public UserController(UserService service) {
        this.service = service;

    }


    @SneakyThrows
    @PostMapping("/login")
    public Object login(@RequestBody UserDto userDto) {
        Object user = service.login(userDto);
        return user;

    }



    @SneakyThrows
    @PostMapping("/register")
    public Object register(@RequestBody UserDto userDto){
        return service.register(userDto);


    }
}
