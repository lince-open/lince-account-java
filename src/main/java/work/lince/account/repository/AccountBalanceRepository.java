package work.lince.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.lince.account.model.AccountBalance;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {

}

