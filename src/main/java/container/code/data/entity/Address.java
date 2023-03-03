package container.code.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")
    private District district;

}
