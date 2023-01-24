package tim5.pcc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_request")
public class PaymentRequest {

    @Id
    @SequenceGenerator(name = "pr_sequence_generator", sequenceName = "pr_sequence", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pr_sequence_generator")
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "acquirer_order_id")
    private String acquirerOrderId;
    @Column(name = "acquirer_timestamp")
    private LocalDateTime acquirerTimestamp;
    @Column(name = "pan_number")
    private String panNumber;
    @Column(name = "security_code")
    private String securityCode;
    @Column(name = "card_holder_name")
    private String cardHolderName;
    @Column(name = "valid_until")
    private LocalDateTime validUntil;
    @Column(name = "amount")
    private double amount;
}
