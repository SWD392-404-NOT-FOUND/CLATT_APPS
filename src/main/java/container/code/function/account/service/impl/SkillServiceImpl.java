package container.code.function.account.service.impl;

import container.code.data.dto.SkillDTO;
import container.code.data.entity.Skill;
import container.code.data.repository.SkillRepository;
import container.code.function.account.service.SkillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    @Autowired
    private  SkillRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SkillDTO save(SkillDTO imageDTO) {
        Skill skill = modelMapper.map(imageDTO, Skill.class);
        skill = skillRepository.save(skill);
        return modelMapper.map(skill, SkillDTO.class);
    }



    @Override
    public Optional<SkillDTO> update(SkillDTO imageDTO) {
        return skillRepository
                .findById(imageDTO.getId())
                .map(existingImage -> {modelMapper.map(imageDTO, existingImage);return existingImage; })
                .map(skillRepository::save)
                .map(b -> {return modelMapper.map(b, SkillDTO.class);});
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SkillDTO> findAll(Pageable pageable) {
        return skillRepository.findAll(pageable).map(i -> {return modelMapper.map(i, SkillDTO.class);});
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SkillDTO> findOne(Integer id) {
        return skillRepository.findById(id).map(i -> { return modelMapper.map(i, SkillDTO.class);});
    }

    @Override
    public void delete(Integer id) {
        skillRepository.deleteById(id);
    }
}
