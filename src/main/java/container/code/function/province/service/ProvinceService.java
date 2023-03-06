package container.code.function.province.service;

import container.code.data.entity.Province;

import java.util.Optional;

public interface ProvinceService {
    Optional<Province> getProvinceById(Integer Id);
}
