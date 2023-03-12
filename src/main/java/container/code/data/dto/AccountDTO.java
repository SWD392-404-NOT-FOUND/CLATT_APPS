package container.code.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import container.code.data.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Integer id;

    private String role;
    private Integer amount;

    private String username;

    private String fullname;
    private LocalDateTime dateOfBirth;

    private String gender;

    private String phone;

    private String email;

    private String bio;

    private String profilePicture;

    private String fcmToken;

    private List<String> locations;
}
