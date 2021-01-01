package m.mirzaeyan.cart.units;

import m.mirzaeyan.cart.domain.Cart;
import m.mirzaeyan.cart.domain.User;
import m.mirzaeyan.cart.repository.UserRepository;
import m.mirzaeyan.cart.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
@ComponentScan(basePackages = "m.mirzaeyan.cart")
public class CartServiceTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;


    @Test
    void addNormalCart() {
        User user = userRepository.findByUserName("mohammad");

        Cart cart = Cart.builder()
                .user(user)
                .cartNumber("6104337954678912")
                .cvv2("5566")
                .expireDate("04/08")
                .pin("5535")
                .pin2("55443")
                .balance(1000000D)
                .build();
        cart.setVersion(0);

        cart = cartService.save(cart);

        assertThat(cart.getId()).isNotNull();
    }

    @Test
    void addFaildCart() {
        User user = userRepository.findByUserName("mohammad");

        Cart cart = Cart.builder()
                .user(user)
                .build();

        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> {
                    cartService.save(cart);
                })
                .withMessageContaining("not-null property references a null or transient value")
                .withMessageContaining(Cart.class.getCanonicalName())
                .withMessageContaining("number");
    }
}
