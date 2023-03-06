package container.code.function.province.service.impl;

import container.code.data.entity.Province;
import container.code.data.repository.ProvinceRepository;
import container.code.function.province.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Override
    public Optional<Province> getProvinceById(Integer Id) { return provinceRepository.findById(Id);}
}
