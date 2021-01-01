package m.mirzaeyan.cart.service.impl.banksServices;


import org.springframework.stereotype.Service;

@Service
public class SecoundPaymentProviderImpl extends TransferServiceImpl{
    public SecoundPaymentProviderImpl() {
        super("http://56e43.mocklab.io/cards/pay");
    }
}
