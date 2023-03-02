package container.code.function.address;

import container.code.data.entity.Address;
import container.code.function.address.api.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getAllAddress();
    List<Address> getAddressesByAccount(int accountId);
    List<AddressResponse> findAddressByAccountId(int accountId);
    void addAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(Address address);
}
