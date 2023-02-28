package container.code.function.account.service.impl;

import container.code.data.dto.JobDTO;
import container.code.data.dto.SkillDTO;
import container.code.data.entity.Job;
import container.code.data.entity.Skill;
import container.code.data.repository.JobRepository;
import container.code.data.repository.SkillRepository;
import container.code.function.account.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public JobDTO save(JobDTO imageDTO) {
        Job skill = modelMapper.map(imageDTO, Job.class);
        skill = skillRepository.save(skill);
        return modelMapper.map(skill, JobDTO.class);
    }



    @Override
    public Optional<JobDTO> update(JobDTO imageDTO) {
        return skillRepository
                .findById(imageDTO.getId())
                .map(existingImage -> {modelMapper.map(imageDTO, existingImage);return existingImage; })
                .map(skillRepository::save)
                .map(b -> {return modelMapper.map(b, JobDTO.class);});
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JobDTO> findAll(Pageable pageable) {
        return skillRepository.findAll(pageable).map(i -> {return modelMapper.map(i, JobDTO.class);});
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JobDTO> findOne(Integer id) {
        return skillRepository.findById(id).map(i -> { return modelMapper.map(i, JobDTO.class);});
    }

    @Override
    public void delete(Integer id) {
        skillRepository.deleteById(id);
    }

}
