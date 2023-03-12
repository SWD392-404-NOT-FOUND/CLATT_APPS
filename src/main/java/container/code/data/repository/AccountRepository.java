package container.code.data.repository;

import container.code.data.dto.ResponseObject;
import container.code.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT t FROM Account t WHERE t.isLocked = FALSE")
    List<Account> findAll();

    @Query("SELECT t FROM Account t WHERE t.id = ?1 and t.isLocked = FALSE")
    Optional<Account> findById(Integer Id);

    @Query("SELECT t FROM Account t WHERE t.role = ?1 and t.isLocked = FALSE")
    List<Account> findByRole(String role);

    @Query("SELECT t FROM Account t WHERE t.email = ?1 and t.isLocked = FALSE")
    Account findByEmail(String email);

    @Query("SELECT t FROM Account t WHERE t.username = ?1 and t.isLocked = FALSE")
    Account findByUsername(String username);

    @Modifying
    @Query("Update Account t SET t.isLocked = TRUE WHERE t.id = ?1")
    void banAccount(Integer id);

    @Modifying
    @Query("Update Account t SET t.isLocked = FALSE WHERE t.id = ?1")
    void unbanAccount(Integer id);

    @Query("SELECT t FROM Account t WHERE t.isLocked = FALSE and t.id NOT IN " +
            "(SELECT f.id FROM BookingOrder f WHERE YEAR(f.workDate) = YEAR(?1) and MONTH(f.workDate) = MONTH(?1) and DAY(f.workDate) = DAY(?1))")
    List<Account> getAccountFromOrderWithDate(LocalDateTime date);

    @Query("SELECT COUNT(t) FROM Account t WHERE t.role != 'admin' and t.isLocked = FALSE")
    long countNotAdmin();
}
