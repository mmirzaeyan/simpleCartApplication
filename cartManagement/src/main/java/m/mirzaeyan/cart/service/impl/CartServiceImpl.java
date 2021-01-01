package m.mirzaeyan.cart.service.impl;

import m.mirzaeyan.cart.domain.Cart;
import m.mirzaeyan.cart.repository.CartRepository;
import m.mirzaeyan.cart.service.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends GenericServiceImpl<Cart, String> implements CartService {

    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository repository) {
        super(repository);
        this.cartRepository = repository;
    }


    @Override
    public Cart findByCarNumber(String cartNumber) {
        return cartRepository.findByCarNumber(cartNumber);
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        // TODO: 2020-12-30
        //Search Based On Authenticated User
        return cartRepository.findAll(pageable);
    }
}
