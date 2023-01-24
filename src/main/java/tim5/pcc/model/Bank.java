package tim5.pcc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @SequenceGenerator(name = "bank_sequence_generator", sequenceName = "bank_sequence", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_sequence_generator")
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "url", unique = true)
    private String url;

    @Column(name = "id_digits", unique = true)
    private String panNumber;
}
