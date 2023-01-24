package tim5.pcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tim5.pcc.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

}
