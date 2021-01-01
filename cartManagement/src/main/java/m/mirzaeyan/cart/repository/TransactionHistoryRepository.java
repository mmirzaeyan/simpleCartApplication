package m.mirzaeyan.cart.repository;


import m.mirzaeyan.cart.domain.TransactionHistory;
import m.mirzaeyan.cart.dto.TransactionsReportDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionHistoryRepository extends GenericRepository<TransactionHistory, String> {

    @Query(nativeQuery = true)
    List<TransactionsReportDto> getReportTransactionHistory(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);


    @Query(value = "from TransactionHistory e where e.createdDate<=delayDated")
    public List<TransactionHistory> findFirstRegisterTransactions(Date delayDated);
}
