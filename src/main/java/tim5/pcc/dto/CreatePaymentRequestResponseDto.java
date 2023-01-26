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
public class CreatePaymentRequestResponseDto {

    private Long ACQUIRER_ORDER_ID;

    private LocalDateTime ACQUIRER_TIMESTAMP;

    private Long ISSUER_ORDER_ID;

    private LocalDateTime ISSUER_TIMESTAMP;

    private String STATUS;

}
