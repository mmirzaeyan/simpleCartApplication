package m.mirzaeyan.cart.dto.cart;

import lombok.*;
import m.mirzaeyan.cart.dto.BaseDTO;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto extends BaseDTO {

    private String cartNumber;
    private String cvv2;
    private String expireDate;
    private String pin;
    private String pin2;
    private Double balance;
    private String userId;
    private String userName;
}
