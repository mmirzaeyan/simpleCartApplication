package m.mirzaeyan.cart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BaseDTO implements Serializable {

    private String id;

    private String ip = "127.0.0.1";

    private Integer version = Integer.valueOf(0);

    @JsonIgnore
    private Date createdDate;

    @JsonIgnore
    private Date updatedDate;
}
