package m.mirzaeyan.cart.service;

import m.mirzaeyan.cart.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService extends GenericService<Cart, String> {

    Cart findByCarNumber(String cartNumber);

    Page<Cart> findAll(Pageable pageable);
}
