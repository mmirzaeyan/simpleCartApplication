package m.mirzaeyan.cart.service.impl;

import m.mirzaeyan.cart.domain.NotificationMessage;
import m.mirzaeyan.cart.domain.TransactionHistory;
import m.mirzaeyan.cart.domain.TransactionStatus;
import m.mirzaeyan.cart.dto.PaymentDto;
import m.mirzaeyan.cart.dto.TransactionsReportDto;
import m.mirzaeyan.cart.repository.TransactionHistoryRepository;
import m.mirzaeyan.cart.service.CartService;
import m.mirzaeyan.cart.service.TransactionHistoryService;
import m.mirzaeyan.cart.service.impl.banksServices.FirstPaymentProviderImpl;
import m.mirzaeyan.cart.service.impl.banksServices.SecoundPaymentProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TransactionHistoryServiceImpl extends GenericServiceImpl<TransactionHistory, String> implements TransactionHistoryService {

    @Autowired
    private FirstPaymentProviderImpl firstPaymentProvider;

    @Autowired
    private SecoundPaymentProviderImpl secoundPaymentProvider;


    @Autowired
    private KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    @Autowired
    private CartService cartService;

    private TransactionHistoryRepository transactionHistoryRepository;


    public TransactionHistoryServiceImpl(TransactionHistoryRepository transactionHistoryRepository) {
        super(transactionHistoryRepository);
        this.transactionHistoryRepository = transactionHistoryRepository;

    }


    @Transactional
    @Override
    public Boolean payment(PaymentDto paymentDto) {
        Boolean paymentResult;
        TransactionHistory transactionHistory = saveRecord(paymentDto);

        if (transactionHistory.getSource().getCartNumber().startsWith("6037")) {
            paymentResult = firstPaymentProvider.transfer(paymentDto);
        } else {
            paymentResult = secoundPaymentProvider.transfer(paymentDto);
        }

        if (paymentResult) {
            this.kafkaTemplate.send("sms", new NotificationMessage("09128908987", "برداشت از حساب"));


            transactionHistory.setTransactionStatus(TransactionStatus.success);
            return true;
        }


        return false;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionHistory saveRecord(PaymentDto paymentDto) {
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .source(cartService.findByCarNumber(paymentDto.getSourceCardNumber()))
                .destination(paymentDto.getDestinationCartNumber())
                .amount(paymentDto.getAmount())
                .transactionStatus(TransactionStatus.firstRegister)
                .build();
        transactionHistory.setVersion(0);
        return repository.save(transactionHistory);
    }

    @Override
    public List<TransactionsReportDto> getTransactionsReport(Date fromDate, Date toDate) {
        return transactionHistoryRepository.getReportTransactionHistory(fromDate, toDate);
    }

    @Transactional
    @Scheduled(cron = "0 0/2 * * * *")
    public void updateTransactionToFaild() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, -2);
        List<TransactionHistory> transactionHistories = transactionHistoryRepository.findFirstRegisterTransactions(calendar.getTime());
        transactionHistories.forEach(i -> {
            i.setTransactionStatus(TransactionStatus.failed);
            repository.save(i);
        });
    }

}
