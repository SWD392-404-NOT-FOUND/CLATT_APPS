package container.code.function.district.impl;

import container.code.data.repository.DistrictRepository;
import container.code.function.district.DistrictMapper;
import container.code.function.district.DistrictService;
import container.code.function.district.api.DistrictResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DistrictResponse> getAllDistrict(Integer provinceId) {
        return  districtRepository.findAllByProvinceId(provinceId)
                .stream()
                .map(districtMapper::toDistrictResponse)
                .collect(Collectors.toList());
    }
}
