package container.code.data.dto;

import container.code.data.entity.*;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
public class AccountDTO {

    private Integer id;
    private String role;
    private Integer amount;
    private String username;
    private String fullname;
    private Date dateOfBirth;
    private String gender;
    private String phone;
    private String email;
    private String bio;
    private String profilePicture;
    private List<Address> addresses;

}
