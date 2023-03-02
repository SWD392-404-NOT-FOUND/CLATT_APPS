package container.code.function.address.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {
    private String accountFullName;
    private String districtName;
    private String description;
}
