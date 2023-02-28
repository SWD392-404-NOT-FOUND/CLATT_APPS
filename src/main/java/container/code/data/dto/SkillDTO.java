package container.code.data.dto;

import container.code.data.entity.SkillEmployee;
import container.code.data.entity.SkillJob;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class SkillDTO {


    private Integer id;


    private String name;


    private String thumbnailImage;


//    private List<SkillEmployee> skillEmployees;


//    private List<SkillJob> skillJobs;
}
