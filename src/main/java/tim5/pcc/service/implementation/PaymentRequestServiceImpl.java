package tim5.pcc.service.implementation;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tim5.pcc.dto.CreatePaymentRequestRequestDto;
import tim5.pcc.dto.FundsReservationResponseDto;
import tim5.pcc.model.Bank;
import tim5.pcc.model.PaymentRequest;
import tim5.pcc.repository.PaymentRequestRepository;
import tim5.pcc.service.template.BankService;
import tim5.pcc.service.template.PaymentRequestService;

import java.util.List;

@Service
public class PaymentRequestServiceImpl implements PaymentRequestService {

    @Autowired
    private BankService bankService;
    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    @Override
    public PaymentRequest create(PaymentRequest paymentRequest) {
        return paymentRequestRepository.save(paymentRequest);
    }

    @Override
    public PaymentRequest getById(Long id) {
        return paymentRequestRepository.findById(id).orElse(null);
    }

    @Override
    public List<PaymentRequest> getAll() {
        return paymentRequestRepository.findAll();
    }

    @Override
    public PaymentRequest update(PaymentRequest paymentRequest) {
        return paymentRequestRepository.save(paymentRequest);
    }

    @Override
    public void delete(Long id) {
        paymentRequestRepository.deleteById(id);
    }

    @Override
    public PaymentRequest processNewPaymentRequest(CreatePaymentRequestRequestDto createPaymentRequestRequestDto) {
        Bank bank = bankService.getByIdDigits(createPaymentRequestRequestDto.getPan().substring(0,3));
        PaymentRequest paymentRequest = create(paymentRequestInit(createPaymentRequestRequestDto));
        if(bank == null){
            paymentRequest.setStatus("ERROR");
            update(paymentRequest);
            return null;
        }else{
            FundsReservationResponseDto fundsReservationResponseDto = sendFundsReservationRequestToBank(bank, paymentRequest);
            paymentRequest.setStatus(fundsReservationResponseDto.getStatus());
            update(paymentRequest);
            return paymentRequest;
        }
    }

    private static FundsReservationResponseDto sendFundsReservationRequestToBank(Bank bank, PaymentRequest paymentRequest) {
        String pccUrl = bank.getUrl() + "/fundsReservation";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject obj = new JSONObject();
        try {
            obj.put("acquirer_order_id", paymentRequest.getAcquirerOrderId());
            obj.put("acquirer_timestamp", paymentRequest.getAcquirerTimestamp());
            obj.put("pan", paymentRequest.getPanNumber());
            obj.put("securityCode", paymentRequest.getSecurityCode());
            obj.put("cardHolderName", paymentRequest.getCardHolderName());
            obj.put("validUntil", paymentRequest.getValidUntil());
            obj.put("amount", paymentRequest.getAmount());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpEntity<String> request = new HttpEntity<>(obj.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(pccUrl, request, FundsReservationResponseDto.class);
    }

    private static PaymentRequest paymentRequestInit(CreatePaymentRequestRequestDto createPaymentRequestRequestDto) {
        return new PaymentRequest(null, createPaymentRequestRequestDto.getAcquirer_order_id(),
                createPaymentRequestRequestDto.getAcquirer_timestamp(), null, null,
                createPaymentRequestRequestDto.getPan(), createPaymentRequestRequestDto.getSecurityCode(),
                createPaymentRequestRequestDto.getCardHolderName(), createPaymentRequestRequestDto.getValidUntil(),
                createPaymentRequestRequestDto.getAmount(), "CREATED");
    }
}
