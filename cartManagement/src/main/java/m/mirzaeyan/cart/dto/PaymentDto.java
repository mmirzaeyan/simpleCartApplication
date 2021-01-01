package m.mirzaeyan.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {

    private String sourceCardId;
    private String sourceCardNumber;
    private String destinationCartNumber;
    private String cvv2;
    private String pin2;
    private String expirationDate;
    private Double amount;
}
