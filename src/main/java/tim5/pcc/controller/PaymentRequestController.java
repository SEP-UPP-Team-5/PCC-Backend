package tim5.pcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tim5.pcc.dto.BankDto;
import tim5.pcc.dto.CreatePaymentRequestRequestDto;
import tim5.pcc.dto.CreatePaymentRequestResponseDto;
import tim5.pcc.model.Bank;
import tim5.pcc.model.PaymentRequest;
import tim5.pcc.service.template.PaymentRequestService;

@RestController
@RequestMapping(value= "/paymentRequest", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentRequestController {

    @Autowired
    private PaymentRequestService paymentRequestService;

    @PostMapping()
    public ResponseEntity<CreatePaymentRequestResponseDto> create(@RequestBody CreatePaymentRequestRequestDto createPaymentRequestRequestDto) {
        CreatePaymentRequestResponseDto createPaymentRequestResponseDto = createPaymentRequestResponseDto(createPaymentRequestRequestDto);
        if (validateFieldValues(createPaymentRequestRequestDto))
            return new ResponseEntity<>(createPaymentRequestResponseDto, HttpStatus.OK);
        PaymentRequest paymentRequest = paymentRequestService.processNewPaymentRequest(createPaymentRequestRequestDto);
        System.out.println("paymentRequest");
        if(paymentRequest == null)
            return new ResponseEntity<>(createPaymentRequestResponseDto, HttpStatus.OK);
        System.out.println("paymentRequest2");
        return new ResponseEntity<>(updateCreatePaymentRequestResponseDto(createPaymentRequestResponseDto, paymentRequest), HttpStatus.OK);
    }

    private CreatePaymentRequestResponseDto updateCreatePaymentRequestResponseDto(CreatePaymentRequestResponseDto createPaymentRequestResponseDto, PaymentRequest paymentRequest) {
        createPaymentRequestResponseDto.setStatus(paymentRequest.getStatus());
        createPaymentRequestResponseDto.setIssuer_order_id(paymentRequest.getIssuerOrderId());
        createPaymentRequestResponseDto.setIssuer_timestamp(paymentRequest.getIssuerTimestamp());
        return createPaymentRequestResponseDto;
    }

    private static CreatePaymentRequestResponseDto createPaymentRequestResponseDto(CreatePaymentRequestRequestDto createPaymentRequestRequestDto) {
        return new CreatePaymentRequestResponseDto(
                createPaymentRequestRequestDto.getAcquirer_order_id(),
                createPaymentRequestRequestDto.getAcquirer_timestamp(),
                null,
                null,
                "ERROR"
        );
    }

    private static boolean validateFieldValues(CreatePaymentRequestRequestDto createPaymentRequestRequestDto) {
        return isNullOrEmpty(createPaymentRequestRequestDto.getAcquirer_order_id().toString(),
                createPaymentRequestRequestDto.getAcquirer_timestamp().toString(),
                createPaymentRequestRequestDto.getPan(), createPaymentRequestRequestDto.getSecurityCode(),
                createPaymentRequestRequestDto.getCardHolderName(),
                createPaymentRequestRequestDto.getValidUntil().toString(),
                String.valueOf(createPaymentRequestRequestDto.getAmount()));
    }

    private static boolean isNullOrEmpty (String...strArr){
        for (String st : strArr) {
            if (st == null || st.equals(""))
                return true;

        }
        return false;
    }
}
