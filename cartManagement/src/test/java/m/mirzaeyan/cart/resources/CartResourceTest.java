package m.mirzaeyan.cart.resources;


import m.mirzaeyan.cart.domain.Cart;
import m.mirzaeyan.cart.domain.User;
import m.mirzaeyan.cart.dto.cart.CartDto;
import m.mirzaeyan.cart.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest()
@ComponentScan(basePackages = "m.mirzaeyan.cart")
public class CartResourceTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    public static final String URL = "http://localhost:8087";

    @Test
    public void addNormalCart() {
        User user = userRepository.findByUserName("mohammad");

        // TODO: 2020-12-31
        // generate random cart number
        CartDto cartDto = CartDto.builder()
            .userId(user.getId())
            .cartNumber("6104337954678912")
            .cvv2("5566")
            .expireDate("04/08")
            .pin("5535")
            .pin2("55443")
            .balance(100000D)
            .build();

        HttpEntity<CartDto> entity = new HttpEntity<>(cartDto, headers);
        ResponseEntity<Cart> exchange = restTemplate.exchange(URL.concat("/api/cart"),
            HttpMethod.POST, entity, Cart.class);
        Cart cart = exchange.getBody();
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(cart.getCartNumber()).isEqualTo(cart.getCartNumber());

    }
}
