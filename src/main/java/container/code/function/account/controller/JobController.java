package container.code.function.account.controller;

import container.code.data.dto.JobDTO;
import container.code.data.dto.SkillDTO;
import container.code.data.repository.JobRepository;
import container.code.data.repository.SkillRepository;
import container.code.function.account.service.JobService;
import container.code.function.account.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    private JobService imageService;
    @Autowired
    private JobRepository imageRepository;


    @PostMapping("/job")
    public ResponseEntity<JobDTO> createImage(@RequestBody JobDTO imageDTO) {

        if (imageDTO.getId() != null) {
            throw new IllegalArgumentException("A new image cannot already have an ID  : idexists");
        }
        JobDTO result = imageService.save(imageDTO);
        return ResponseEntity.ok(result);

    }

    @PutMapping("/job/{id}")
    public ResponseEntity<?> updateImage(
            @PathVariable(value = "id", required = false) final Integer id,
            @RequestBody JobDTO imageDTO ) {

        if (imageDTO.getId() == null) {
            throw new IllegalArgumentException("Invalid id  : id null");
        }
        if (!Objects.equals(id, imageDTO.getId())) {
            throw new IllegalArgumentException("Invalid ID  : id invalid");
        }

        if (!imageRepository.existsById(id)) {
            throw new IllegalArgumentException("Entity not found  : id notfound");
        }

        Optional<JobDTO> result = imageService.update(imageDTO);

        return result.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/job")
    public ResponseEntity<?> getAllImages(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        Page<JobDTO> page = imageService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<?> getImage(@PathVariable Integer id) {
        Optional<JobDTO> imageDTO = imageService.findOne(id);
        return imageDTO.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Integer id) {
        imageService.delete(id);
        return ResponseEntity.ok().body("OK");
    }
}
