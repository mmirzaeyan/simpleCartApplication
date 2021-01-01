package m.mirzaeyan.cart.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import m.mirzaeyan.cart.dto.TransactionsReportDto;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION_HISTORY")
@Getter
@Setter
@Builder
@SqlResultSetMapping(
        name = "reportDetails",
        classes = {
                @ConstructorResult(
                        targetClass = TransactionsReportDto.class,
                        columns = {
                                @ColumnResult(name = "source_card", type = String.class),
                                @ColumnResult(name = "countSuccessTransaction", type = Long.class),
                                @ColumnResult(name = "countFaildTransactions", type = Long.class),
                                @ColumnResult(name = "sumAmount", type = Double.class)
                        }
                )
        }
)

@NamedNativeQuery(name = "TransactionHistory.getReportTransactionHistory",
        query = "select c.CART_NUMBER as cartNumber, " +
                "       sum(mainSelect.success) as countSuccessTransaction, " +
                "       sum(mainSelect.fail) as countFaildTransactions, " +
                "       sum(mainSelect.amount) as sumAmount" +
                "from ( " +
                "         select c.SOURCE_CART_ID, " +
                "                case when TRANSACTION_STATUS =  1 then 1 else 0 end as success, " +
                "                case when TRANSACTION_STATUS = 2 then 1 else 0 end as fail, " +
                "                c.AMOUNT " +
                "         from Transaction_History c where c.createddate >= :fromDate " +
                "           and c.createddate <= :toDate ) mainSelect " +
                " inner join CART c on c.id = SOURCE_CART_ID " +
                "group by mainSelect.SOURCE_CART_ID", resultSetMapping = "reportDetails")
public class TransactionHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_CART_ID", updatable = false, nullable = false)
    private Cart source;

    @Column(name = "DESTINATION", nullable = false)
    private String destination;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Enumerated
    @Column(name = "TRANSACTION_STATUS", columnDefinition = "smallint", nullable = false)
    private TransactionStatus transactionStatus;
}
