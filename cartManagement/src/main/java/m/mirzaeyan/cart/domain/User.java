package m.mirzaeyan.cart.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "USER")
@Entity
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "USERNAME", nullable = false)
    private String userName;

    public User(String id) {
        this.setId(id);
    }


}
