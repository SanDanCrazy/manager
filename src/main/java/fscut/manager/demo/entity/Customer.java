package fscut.manager.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    public interface SimpleView{};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(SimpleView.class)
    private Integer id;

    @JsonView(SimpleView.class)
    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @JsonView(SimpleView.class)
    @Column(name = "realname", nullable = false)
    private String realName;

    @JsonIgnore
    @Column(name = "product_id", nullable = true)
    private Integer productId;

    public Customer() {
    }

    public Customer(Integer id, String username, String password, String realName, Integer productId){
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.productId = productId;
    }

}
