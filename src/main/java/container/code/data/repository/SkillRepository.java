package container.code.data.repository;

import container.code.data.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository  extends JpaRepository<Skill, Integer> {

}
