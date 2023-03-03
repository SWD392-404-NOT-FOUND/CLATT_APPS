package container.code.function.account.service.impl;

import container.code.data.dto.AccountDTO;
import container.code.data.dto.JobDTO;
import container.code.data.entity.Account;
import container.code.data.repository.AccountRepository;
import container.code.data.repository.JobRepository;
import container.code.function.account.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Optional<AccountDTO> update(AccountDTO imageDTO) {
        return skillRepository
                .findById(imageDTO.getId())
                .map(existingImage -> {
                    existingImage.getAddresses().clear();
                    modelMapper.map(imageDTO, existingImage);
                    existingImage.getAddresses().forEach(c->c.setAccount(existingImage));
                    return existingImage;
                })
                .map(skillRepository::save)
                .map(b -> {return modelMapper.map(b, AccountDTO.class);});
    }

    @Override
    public Page<AccountDTO> findAll(Pageable pageable) {
        return skillRepository.findAll(pageable).map(i -> {return modelMapper.map(i, AccountDTO.class);});
    }

    @Override
    public Optional<AccountDTO> findOne(Integer id) {
        return skillRepository.findById(id).map(i -> { return modelMapper.map(i, AccountDTO.class);});
    }
}
