package container.code.function.address.service.impl;

import container.code.data.entity.Address;
import container.code.data.repository.AddressRepository;
import container.code.function.address.AddressMapper;
import container.code.function.address.service.AddressService;
import container.code.function.address.api.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<AddressResponse> findAddressByAccountId(int accountId){
        return addressRepository.findAddressByAccountId(accountId)
                .stream()
                .map(addressMapper::toAddressResponse)
                .collect(Collectors.toList());
    }
    @Override
    public List<AddressResponse> getAllAddress() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toAddressResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> getAddressesByAccount(int accountId) {
        return addressRepository.getAddressesByAccount(accountId);
    }

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }
}
