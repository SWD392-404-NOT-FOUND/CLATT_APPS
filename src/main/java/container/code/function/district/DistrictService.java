package container.code.function.district;

import container.code.function.district.api.DistrictResponse;

import java.util.List;

public interface DistrictService {
    List<DistrictResponse> getAllDistrict(Integer provinceId);
}
