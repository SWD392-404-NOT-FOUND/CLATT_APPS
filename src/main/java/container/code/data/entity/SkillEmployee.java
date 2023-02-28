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
public class SkillEmployee {
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
    @JsonIgnoreProperties(value = { "addresses", "historyAmounts", "notifications", "employeeOrders", "skillEmployees", "bookingOrders" }, allowSetters = true)
    @JoinColumn(name = "account_id")
    private Account account;
}
