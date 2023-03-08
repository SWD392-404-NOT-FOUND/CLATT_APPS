package container.code.function.district;

import container.code.data.dto.ResponseObject;
import container.code.function.district.api.DistrictResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DistrictService {
    ResponseEntity<ResponseObject> getAllDistrict(Integer provinceId);
}
