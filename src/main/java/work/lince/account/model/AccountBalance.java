package work.lince.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "TB_ACCOUNTBALANCE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalance {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BALANCE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime date;

    @Column(name = "BALANCE_VALUE", updatable=false, precision=12, scale=2)
    private BigDecimal value;
}
