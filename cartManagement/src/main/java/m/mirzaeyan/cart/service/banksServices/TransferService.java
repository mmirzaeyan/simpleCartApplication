package m.mirzaeyan.cart.service.banksServices;

import m.mirzaeyan.cart.dto.PaymentDto;

public interface TransferService {
    public boolean transfer(PaymentDto paymentDto);
}
