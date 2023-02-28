package container.code.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class SkillJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "skill_id")
    @JsonIgnoreProperties(value = { "skillEmployees", "skillJobs" }, allowSetters = true)
    private Skill skill;


    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "job_id")
    @JsonIgnoreProperties(value = { "orderJobs", "skillJobs" }, allowSetters = true)
    private Job job;
}
