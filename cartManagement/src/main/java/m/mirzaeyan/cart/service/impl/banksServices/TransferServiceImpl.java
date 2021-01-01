package m.mirzaeyan.cart.service.impl.banksServices;

import m.mirzaeyan.cart.dto.PaymentDto;
import m.mirzaeyan.cart.service.banksServices.TransferService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;


@Service
public abstract class TransferServiceImpl implements TransferService {

    protected final String url;


    TransferServiceImpl(String url) {
        this.url = url;
    }

    @Override
    public boolean transfer(PaymentDto paymentDto) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setBufferRequestBody(false);
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpEntity<PaymentDto> request = new HttpEntity<>(new PaymentDto());
        ResponseEntity<HttpServletResponse> response = restTemplate
            .postForEntity(url, request, HttpServletResponse.class);
        HttpStatus httpStatus = response.getStatusCode();

        if (httpStatus != null && httpStatus.equals(HttpStatus.OK))
            return true;
        else
            return false;
    }
}
