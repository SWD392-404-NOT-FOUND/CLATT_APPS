package container.code.function.district.impl;

import container.code.data.dto.ResponseObject;
import container.code.data.repository.DistrictRepository;
import container.code.function.district.DistrictMapper;
import container.code.function.district.DistrictService;
import container.code.function.district.api.DistrictResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public ResponseEntity<ResponseObject> getAllDistrict(Integer provinceId) {
        try {
            List<DistrictResponse> list = districtRepository.findAllByProvinceId(provinceId)
                    .stream()
                    .map(districtMapper::toDistrictResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), null, list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur!", null));
        }
    }
}
