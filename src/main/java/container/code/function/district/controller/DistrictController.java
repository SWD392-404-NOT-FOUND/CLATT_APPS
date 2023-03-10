package container.code.function.district.controller;

import container.code.data.dto.ResponseObject;
import container.code.function.district.DistrictService;
import container.code.function.district.api.DistrictResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3030", allowCredentials = "true")
@RequestMapping(value = "/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping("/{province_id}")
    @PreAuthorize("hasAnyAuthority('admin', 'renter', 'employee')")
    public ResponseEntity<ResponseObject> getAll(@PathVariable("province_id") int province_id) {
        return districtService.getAllDistrict(province_id);
    }
}
