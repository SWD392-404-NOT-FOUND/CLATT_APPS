package container.code.data.dto;

import container.code.data.entity.OrderJob;
import container.code.data.entity.SkillJob;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class JobDTO {


    private Integer id;


    private String name;

    private Integer price;


    private Integer measureValue;


    private String measureUnit;


    private String thumbnailJobImage;


//    private List<OrderJob> orderJobs;


//    private List<SkillJob> skillJobs;
}
