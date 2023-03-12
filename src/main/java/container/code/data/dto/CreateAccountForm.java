package container.code.data.dto;

import lombok.Data;

@Data
public class CreateAccountForm {
    private String username;

    private String password;

    private String role;
}
