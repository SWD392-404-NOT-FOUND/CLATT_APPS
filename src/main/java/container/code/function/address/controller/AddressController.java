package container.code.function.address.controller;

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
    public ResponseEntity<List<AddressResponse>> getAllAddresses(){
        return new ResponseEntity<List<AddressResponse>>(addressService.getAllAddress(),HttpStatus.OK);
    }
    @GetMapping("account/{accountId}")
    public ResponseEntity<List<AddressResponse>> getAddressByAccountId(@PathVariable("accountId") int accountId){
        return new ResponseEntity<List<AddressResponse>>(addressService.findAddressByAccountId(accountId),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createAddress(@RequestBody Address address){
        try{
            addressService.addAddress(address);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("{addressId}")
    public ResponseEntity updateAddress(@PathVariable("addressId") int addressId, @RequestBody Address address){
        try{
            address.setId(addressId);
            addressService.updateAddress(address);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
