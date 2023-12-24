package unitech.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import unitech.model.Role;

public record UserDto(
        Long id,
        String name,
        String surname,

        @NotEmpty(message = "User FIN can not be empty! Please Enter your FIN")
        String fin,

        @NotEmpty(message = "User password can not be empty! Please Enter a Valid Password")

        String password,
        String phoneNumber,
        Role role) {
}
