package container.code.function.address.controller;

import container.code.data.dto.ResponseObject;
import container.code.data.entity.Address;
import container.code.function.address.service.AddressService;
import container.code.function.address.api.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("all")
    public ResponseEntity<ResponseObject> getAllAddresses() {
        return addressService.getAllAddress();
    }

    @GetMapping("account/{accountId}")
    public ResponseEntity<ResponseObject> getAddressByAccountId(@PathVariable("accountId") int accountId) {
        return addressService.findAddressByAccountId(accountId);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @PutMapping("{addressId}")
    public ResponseEntity<ResponseObject> updateAddress(@PathVariable("addressId") int addressId, @RequestBody Address address) {
        address.setId(addressId);
        return addressService.updateAddress(address);
    }
}
