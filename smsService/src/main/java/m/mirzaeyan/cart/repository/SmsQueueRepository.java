package m.mirzaeyan.cart.repository;

import m.mirzaeyan.cart.domain.SmsQueue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SmsQueueRepository extends CrudRepository<SmsQueue, String> {

    @Query(value = "from SmsQueue e where e.createdDate<=delayDated")
    public List<SmsQueue> findFaildSms(Date delayDated);
}
