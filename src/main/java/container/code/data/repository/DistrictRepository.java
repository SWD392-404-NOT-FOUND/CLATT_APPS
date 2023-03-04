package container.code.data.repository;

import container.code.data.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query(value = "SELECT d "+
            "FROM District d  LEFT JOIN d.province pr " +
            "WHERE pr.id = :province_id ")
    List<District> findAllByProvinceId(@Param("province_id") Integer provinceId);
}
