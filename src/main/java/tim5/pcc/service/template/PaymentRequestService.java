package tim5.pcc.service.template;

import tim5.pcc.dto.CreatePaymentRequestRequestDto;
import tim5.pcc.dto.CreatePaymentRequestResponseDto;
import tim5.pcc.model.PaymentRequest;

import java.util.List;

public interface PaymentRequestService {

    PaymentRequest create(PaymentRequest paymentRequest);
    PaymentRequest getById(Long id);
    List<PaymentRequest> getAll();
    PaymentRequest update(PaymentRequest paymentRequest);
    void delete(Long id);
    PaymentRequest processNewPaymentRequest(CreatePaymentRequestRequestDto createPaymentRequestRequestDto);

}