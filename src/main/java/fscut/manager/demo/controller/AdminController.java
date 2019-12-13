package fscut.manager.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fscut.manager.demo.entity.Customer;
import fscut.manager.demo.exception.CustomerAlreadyExitsException;
import fscut.manager.demo.exception.CustomerNotExitsException;
import fscut.manager.demo.service.CustomerService;
import fscut.manager.demo.vo.CustomerAuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("addToProduct")
    public ResponseEntity<String> addToProduct(@RequestBody CustomerAuthVO customerAuthVO){
        customerService.addToProduct(customerAuthVO);
        return ResponseEntity.ok("ok");
    }

    @JsonView({Customer.SimpleView.class})
    @GetMapping("customerList")
    public ResponseEntity<List<Customer>> getCustomerList(){
        List<Customer> customerList = customerService.getCustomerList();
        return ResponseEntity.ok(customerList);
    }

    @JsonView({Customer.SimpleView.class})
    @GetMapping("customerList/{id}")
    public ResponseEntity<List<Customer>> getCustomerListByProductId(@PathVariable("id") Integer productId){
        List<Customer> customerList = customerService.getCustomerListByProductId(productId);
        return ResponseEntity.ok(customerList);
    }

    @DeleteMapping("deleteFromProduct")
    public ResponseEntity<Void> deleteFromProduct(Integer customerId, Integer productId){
        customerService.deleteFromProduct(customerId,productId);
        return ResponseEntity.ok(null);
    }

    @PostMapping("addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws CustomerAlreadyExitsException {
        Customer result = customerService.addCustomer(customer);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("deleteCustomer")
    public ResponseEntity<Void> deleteCustomer(String username) throws CustomerNotExitsException {
        customerService.deleteCustomer(username);
        return ResponseEntity.ok(null);
    }


}
