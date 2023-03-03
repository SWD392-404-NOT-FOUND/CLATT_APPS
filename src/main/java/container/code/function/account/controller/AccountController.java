package container.code.function.account.controller;

import container.code.data.dto.AccountDTO;
import container.code.data.dto.JobDTO;
import container.code.data.entity.Account;
import container.code.data.repository.AccountRepository;
import container.code.data.repository.JobRepository;
import container.code.function.account.service.AccountService;
import container.code.function.account.service.JobService;
import container.code.function.account.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class AccountController {
    @Autowired
    private AccountService imageService;
    @Autowired
    private AccountRepository imageRepository;


    @PutMapping("/account/{id}")
    public ResponseEntity<?> updateImage(
            @PathVariable(value = "id", required = false) final Integer id,
            @RequestBody AccountDTO imageDTO ) {

        if (imageDTO.getId() == null) {
            throw new IllegalArgumentException("Invalid id  : id null");
        }
        if (!Objects.equals(id, imageDTO.getId())) {
            throw new IllegalArgumentException("Invalid ID  : id invalid");
        }

        if (!imageRepository.existsById(id)) {
            throw new IllegalArgumentException("Entity not found  : id notfound");
        }

        Optional<AccountDTO> result = imageService.update(imageDTO);

        return result.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAllImages(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        Page<AccountDTO> page = imageService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getImage(@PathVariable Integer id) {
        Optional<AccountDTO> imageDTO = imageService.findOne(id);
        return imageDTO.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
