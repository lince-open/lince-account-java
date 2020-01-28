package work.lince.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.lince.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

