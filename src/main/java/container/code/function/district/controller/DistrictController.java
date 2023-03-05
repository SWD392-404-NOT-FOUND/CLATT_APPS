package container.code.function.district.controller;

import container.code.function.district.DistrictService;
import container.code.function.district.api.DistrictResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping("/{province_id}")
    public ResponseEntity<List<DistrictResponse>> getAll(@PathVariable("province_id") int province_id){
        return new ResponseEntity<List<DistrictResponse>>(districtService.getAllDistrict(province_id), HttpStatus.OK);
    }
}