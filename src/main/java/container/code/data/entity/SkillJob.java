package container.code.data.entity;

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
    @JoinColumn(name = "skill_id")
    private Skill skill;


    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
}