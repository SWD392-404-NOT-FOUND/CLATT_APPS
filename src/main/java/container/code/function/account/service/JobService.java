package container.code.function.account.service;

import container.code.data.dto.JobDTO;
import container.code.data.dto.SkillDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface JobService {

    JobDTO save(JobDTO imageDTO);
    Optional<JobDTO> update(JobDTO imageDTO);
    Page<JobDTO> findAll(Pageable pageable);
    Optional<JobDTO> findOne(Integer id);
    void delete(Integer id);
}
