package m.mirzaeyan.cart.service;

import m.mirzaeyan.cart.domain.NotificationMessage;
import m.mirzaeyan.cart.domain.SmsQueue;
import m.mirzaeyan.cart.repository.SmsQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SmsQueueService {

    @Autowired
    private SmsQueueRepository smsQueueRepository;

    @KafkaListener(topics = "sms", groupId = "WorkUnitApp")
    public void readMessage(NotificationMessage message) throws IOException {
        this.sendSms(message);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SmsQueue saveSmsQueue(NotificationMessage notificationMessage) {
        SmsQueue smsQueue = SmsQueue.builder()
                .mobilePhone(notificationMessage.getTarget())
                .content(notificationMessage.getMsg())
                .success(false).build();

        return smsQueueRepository.save(smsQueue);
    }

    @Transactional
    public void sendSms(NotificationMessage notificationMessage) {
        SmsQueue smsQueue = saveSmsQueue(notificationMessage);
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setBufferRequestBody(false);
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpEntity<SmsQueue> request = new HttpEntity<>(smsQueue);
        ResponseEntity<HttpServletResponse> response = restTemplate
                .postForEntity("http://56e43.mocklab.io/sendMessage", request, HttpServletResponse.class);
        HttpStatus httpStatus = response.getStatusCode();

        if (httpStatus != null && httpStatus.equals(HttpStatus.OK)) {
            smsQueue.setSuccess(true);
        }
    }


    @Transactional
    @Scheduled(cron = "0 0/2 * * * *")
    public void updateTransactionToFaild() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, -2);
        List<SmsQueue> smsQueues = smsQueueRepository.findFaildSms(calendar.getTime());
        smsQueues.forEach(i -> {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setBufferRequestBody(false);
            RestTemplate restTemplate = new RestTemplate(factory);
            HttpEntity<SmsQueue> request = new HttpEntity<>(i);
            ResponseEntity<HttpServletResponse> response = restTemplate
                    .postForEntity("http://56e43.mocklab.io/sendMessage", request, HttpServletResponse.class);
            HttpStatus httpStatus = response.getStatusCode();

            if (httpStatus != null && httpStatus.equals(HttpStatus.OK)) {
                i.setSuccess(true);
                smsQueueRepository.save(i);
            }

        });
    }


}
