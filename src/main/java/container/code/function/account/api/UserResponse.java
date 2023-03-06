package container.code.function.account.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private Integer id;
    private String role;
    private String name;
    private Date dateOfBirth;
    private String gender;
    private String phone;
    private String email;
    private String bio;
    private String srcPicture;
    private Byte availableHire;
    private Boolean banned;
//    private String location;

}
