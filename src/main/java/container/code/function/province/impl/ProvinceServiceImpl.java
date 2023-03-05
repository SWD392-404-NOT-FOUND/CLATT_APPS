package container.code.function.province.impl;

import container.code.data.repository.ProvinceRepository;
import container.code.function.district.DistrictMapper;
import container.code.function.district.api.DistrictResponse;
import container.code.function.province.ProvinceMapper;
import container.code.function.province.ProvinceService;
import container.code.function.province.api.ProvinceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<ProvinceResponse> getAllProvince() {
        return provinceRepository.findAll()
                .stream()
                .map(provinceMapper::toProvinceResponse)
                .collect(Collectors.toList());
    }
}
