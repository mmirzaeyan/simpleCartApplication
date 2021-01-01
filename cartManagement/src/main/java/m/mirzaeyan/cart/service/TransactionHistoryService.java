package m.mirzaeyan.cart.service;

import m.mirzaeyan.cart.domain.TransactionHistory;
import m.mirzaeyan.cart.dto.PaymentDto;
import m.mirzaeyan.cart.dto.TransactionsReportDto;

import java.util.Date;
import java.util.List;

public interface TransactionHistoryService extends GenericService<TransactionHistory, String> {

    Boolean payment(PaymentDto paymentDto) throws Exception;

    List<TransactionsReportDto> getTransactionsReport(Date fromDate, Date toDate);
}
