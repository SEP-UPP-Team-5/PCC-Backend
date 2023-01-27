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
public class FundsReservationResponseDto {

    private Long acquirer_order_id;

    private LocalDateTime acquirer_timestamp;

    private Long issuer_order_id;

    private LocalDateTime issuer_timestamp;

    private String status;

}
