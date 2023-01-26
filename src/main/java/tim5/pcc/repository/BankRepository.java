package tim5.pcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tim5.pcc.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query(value="SELECT * FROM bank where id_digits=:pan",nativeQuery = true)
    Bank getByIdDigits(@Param("pan")String panNumber);

}
