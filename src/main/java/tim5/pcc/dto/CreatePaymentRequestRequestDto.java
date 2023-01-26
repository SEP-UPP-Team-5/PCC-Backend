package tim5.pcc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequestRequestDto {

    private Long ACQUIRER_ORDER_ID;

    private LocalDateTime ACQUIRER_TIMESTAMP;

    private String PAN;

    private String securityCode;

    private String cardHolderName;

    private LocalDateTime validUntil;

    private double amount;

}
