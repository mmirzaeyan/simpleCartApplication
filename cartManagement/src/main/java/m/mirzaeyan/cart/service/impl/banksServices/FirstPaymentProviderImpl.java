package m.mirzaeyan.cart.service.impl.banksServices;


import org.springframework.stereotype.Service;

@Service
public class FirstPaymentProviderImpl extends  TransferServiceImpl {

    public FirstPaymentProviderImpl() {
        super("http://56e43.mocklab.io/payment/transfer");
    }
}
