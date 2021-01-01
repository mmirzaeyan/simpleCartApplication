package m.mirzaeyan.cart.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionsReportDto {

    private String cartNumber;
    private Long countSuccessTransaction;
    private Long countFaildTransactions;
    private Double sumAmount;

    public TransactionsReportDto(String cartNumber, Long countSuccessTransaction, Long countFaildTransactions, Double sumAmount) {
        this.cartNumber = cartNumber;
        this.countSuccessTransaction = countSuccessTransaction;
        this.countFaildTransactions = countFaildTransactions;
        this.sumAmount = sumAmount;
    }
}
