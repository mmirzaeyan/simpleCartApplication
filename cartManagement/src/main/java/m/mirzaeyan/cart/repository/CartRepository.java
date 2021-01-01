package m.mirzaeyan.cart.repository;


import m.mirzaeyan.cart.domain.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends GenericRepository<Cart, String> {

    @Query(value = "from Cart e where e.cartNumber=:cartNumber")
    public Cart findByCarNumber(String cartNumber);

}
