package m.mirzaeyan.cart.rest;


import m.mirzaeyan.cart.domain.TransactionHistory;
import m.mirzaeyan.cart.dto.PaymentDto;
import m.mirzaeyan.cart.dto.TransactionsReportDto;
import m.mirzaeyan.cart.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactionHistory")
public class TransactionHistoryResource {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @GetMapping
    public ResponseEntity<Page<TransactionHistory>> get() {
        return ResponseEntity.ok(transactionHistoryService.find());
    }

    @PostMapping("/payment")
    public ResponseEntity<Boolean> payment(@RequestBody PaymentDto paymentDto) throws Exception {
        return ResponseEntity.ok(transactionHistoryService.payment(paymentDto));
    }

    @GetMapping("/{fromDate}/{toDate}")
    public ResponseEntity<List<TransactionsReportDto>> getTransactionsReport(@PathVariable("fromDate") Date fromDate, @PathVariable("toDate") Date toDate) {
        return ResponseEntity.ok(transactionHistoryService.getTransactionsReport(fromDate, toDate));
    }


}
