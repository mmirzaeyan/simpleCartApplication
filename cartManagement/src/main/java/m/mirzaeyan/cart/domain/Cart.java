package m.mirzaeyan.cart.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CART")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends BaseEntity {

    @Column(name = "CART_NUMBER", nullable = false)
    private String cartNumber;

    @Column(name = "CVV2", nullable = false)
    private String cvv2;

    @Column(name = "EXPIRE_DATE", nullable = false)
    private String expireDate;

    @Column(name = "PIN", nullable = false)
    private String pin;

    @Column(name = "PIN2")
    private String pin2;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", updatable = false, nullable = false)
    private User user;

    public Cart(String id) {
        this.setId(id);
    }
}
