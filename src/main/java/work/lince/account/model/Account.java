package work.lince.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "TB_ACCOUNT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE",length = 20)
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @NotEmpty
    @Column(name = "TITLE",length = 50)
    private String title;

    @Column(name = "DESCRIPTION",length = 250)
    private String description;

    @Column(name = "STATUS",length = 20)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "OWNER",length = 50)
    private String owner;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "BALANCE_ID",referencedColumnName = "ID")
    private List<AccountBalance> balanceList;
    
}
