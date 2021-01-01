package m.mirzaeyan.cart.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "createddate", updatable = false, nullable = false)
    private Date createdDate = new Date();

    @Column(name = "updateddate", nullable = false)
    private Date updatedDate = new Date();

    @Column(name = "ip", nullable = false)
    private String ip = "127.0.0.1";

    @Column(name = "version", nullable = false)
    private Integer version;

}
