package container.code.function.account.service;

import container.code.data.dto.SkillDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SkillService {

    SkillDTO save(SkillDTO imageDTO);
    Optional<SkillDTO> update(SkillDTO imageDTO);
    Page<SkillDTO> findAll(Pageable pageable);
    Optional<SkillDTO> findOne(Integer id);
    void delete(Integer id);
}
