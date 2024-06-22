package gpb.dus.middle.users.model;

import jakarta.validation.constraints.*;

public record CreateUserRequestV2(Long userId, @NotBlank String userName) {

}

