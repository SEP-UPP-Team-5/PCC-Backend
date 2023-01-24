package tim5.pcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tim5.pcc.model.PaymentRequest;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {

}
