package fscut.manager.demo.dao;

import fscut.manager.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    @Query(value = "select id from customer where realname = ?1", nativeQuery = true)
    Integer findIdByRealName(String realName);

    Customer findCustomerByUsername(String Username);

}
