package fscut.manager.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "realname", nullable = false)
    private String realName;

    @Column(name = "product_id", nullable = true)
    private Integer productId;

}
