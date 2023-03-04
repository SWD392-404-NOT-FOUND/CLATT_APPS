package container.code.function.province.controller;

import container.code.function.address.AddressService;
import container.code.function.address.api.AddressResponse;
import container.code.function.province.ProvinceService;
import container.code.function.province.api.ProvinceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/all")
    public ResponseEntity<List<ProvinceResponse>> getAll(){
        return new ResponseEntity<List<ProvinceResponse>>(provinceService.getAllProvince(), HttpStatus.OK);
    }
}
