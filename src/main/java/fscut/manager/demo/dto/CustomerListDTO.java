package fscut.manager.demo.dto;

import fscut.manager.demo.entity.Customer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CustomerListDTO implements Serializable {

    private List<Customer> developer;

    private List<Customer> designer;

    private List<Customer> tester;

}
