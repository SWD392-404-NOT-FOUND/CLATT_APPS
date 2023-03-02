package container.code.function.address;

import container.code.data.entity.Address;
import container.code.function.address.api.AddressResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressResponse toAddressResponse(Address address){
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setDescription(address.getDescription());
        addressResponse.setDistrictName(address.getDistrict().getName());
        addressResponse.setAccountFullName(address.getAccount().getFullname());
        return addressResponse;
    }
}
