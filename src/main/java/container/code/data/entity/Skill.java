package container.code.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Skill {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @OneToMany(mappedBy = "skill",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "skill", "account" }, allowSetters = true)
    private List<SkillEmployee> skillEmployees;

    @JsonIgnoreProperties(value = { "skill", "job" }, allowSetters = true)
    @OneToMany(mappedBy = "skill",cascade = CascadeType.ALL)
    private List<SkillJob> skillJobs;


}
