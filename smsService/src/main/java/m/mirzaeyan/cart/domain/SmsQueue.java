package m.mirzaeyan.cart.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="SMS_QUEUE")
public class SmsQueue implements Serializable {
    private static final long serialVersionUID = 4295229462159851305L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "MOBILE_PHONE", updatable = false, nullable = false)
    private String mobilePhone;

    @Column(name = "CONTENT", updatable = false, nullable = false)
    private String content;


    @Column(name = "IS_SUCCESS", nullable = false)
    private Boolean success;

    @Column(name = "createddate", nullable = false)
    private Date createdDate;


}
