package m.mirzaeyan.cart.rest;


import m.mirzaeyan.cart.domain.Cart;
import m.mirzaeyan.cart.dto.cart.CartDto;
import m.mirzaeyan.cart.dto.cart.CartMapper;
import m.mirzaeyan.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartResource {


    @Autowired
    private CartService cartService;


    @GetMapping("/{id}")
    public ResponseEntity<CartDto> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(CartMapper.INSTANCE.toDto(cartService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<CartDto>> get( Pageable pageable) {
        return ResponseEntity.ok((cartService.findAll(pageable).map(CartMapper.INSTANCE::toDto)));
    }

    @PostMapping
    public ResponseEntity<Cart> save(@RequestBody CartDto cardDto) {
        return ResponseEntity.ok(cartService.save(CartMapper.INSTANCE.toEntity(cardDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> edit(@RequestBody CartDto cardDto) {
        return ResponseEntity.ok(cartService.save(CartMapper.INSTANCE.toEntity(cardDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception {
        cartService.delete(id);
        return ResponseEntity.ok("Card deleted");
    }
}
