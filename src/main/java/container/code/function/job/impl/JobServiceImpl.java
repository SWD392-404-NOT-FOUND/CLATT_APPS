package container.code.function.job.impl;

import container.code.data.entity.Job;
import container.code.data.repository.JobRepository;
import container.code.function.account.service.filestorage.FileStorage;
import container.code.function.job.JobMapper;
import container.code.function.job.JobService;
import container.code.function.job.api.JobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobServiceImpl implements JobService {
    @Autowired
    private FileStorage fileStorage;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobMapper jobMapper;

    private Job findJob(Integer id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job not found"));
    }

    @Override
    public List<JobResponse> getAllJob() {
        return jobRepository.findAll().stream().map(jobMapper::toJobResponse).collect(Collectors.toList());
    }

    @Override
    public JobResponse getJob(Integer id) {
        Job job = findJob(id);
        return jobMapper.toJobResponse(job);
    }

    @Override
    public void addJob(Job job, MultipartFile file) throws IOException {
        job.setId(null);
        String url = fileStorage.uploadFile(file);
        job.setThumbnailJobImage(url);
        jobRepository.save(job);
    }

    @Override
    public void updateJob(Job job, MultipartFile file) throws IOException {
        Job existJob = findJob(job.getId());
        if (existJob != null) {
            if (file != null) {
                String oldUrl = existJob.getThumbnailJobImage();
                fileStorage.deleteFile(oldUrl);
                String updateUrl = fileStorage.uploadFile(file);
                existJob.setThumbnailJobImage(updateUrl);
            }
            if (job.getPrice() != null) {
                existJob.setName(job.getName());
                existJob.setPrice(job.getPrice());
            }
            if (job.getMeasureUnit() != null) {
                existJob.setMeasureUnit(job.getMeasureUnit());
            }
            if (job.getName() != null) {
                existJob.setName(job.getName());
            }
            jobRepository.save(existJob);
        }
    }

    @Override
    public void deleteJob(Job job) {
        Job existJob = findJob(job.getId());
        String oldUrl = existJob.getThumbnailJobImage();
        fileStorage.deleteFile(oldUrl);
        jobRepository.delete(existJob);
    }
}
