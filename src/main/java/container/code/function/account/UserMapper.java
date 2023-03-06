package container.code.function.account;

import container.code.data.entity.Account;
import container.code.function.account.api.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(Account user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setRole(user.getRole());
        userResponse.setName(user.getFullname());
        userResponse.setDateOfBirth(user.getDateOfBirth());
        userResponse.setGender(user.getGender());
        userResponse.setPhone(user.getPhone());
        userResponse.setBio(user.getBio());
        userResponse.setEmail(user.getEmail());
        userResponse.setSrcPicture(user.getProfilePicture());
        userResponse.setAvailableHire(user.getAvailableHire());
        userResponse.setBanned(user.getBanned());
        return userResponse;
    }
}
