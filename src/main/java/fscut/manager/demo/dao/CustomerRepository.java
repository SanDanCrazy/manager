package fscut.manager.demo.dao;

import fscut.manager.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    @Query(value = "select id from customer where realname = ?1", nativeQuery = true)
    Integer findIdByRealName(String realName);

    Customer findCustomerByUsername(String Username);

    @Query(value = "select role_code from customer as c right join customer_role as cr " +
            "on c.id = cr.customer_id left join role as r " +
            "on cr.role_id = r.id where c.id = :userId and cr.product_id in :productIds ", nativeQuery = true)
    List<String> findRolesByCustomerIdAndProductIds(@Param("userId") Integer userId, @Param("productIds") List<Integer> productIds);

    @Query(value = "select DISTINCT product_id from customer_role where customer_id = ?1 order by product_id ", nativeQuery = true)
    List<Integer> findProductIdsByCustomerId(Integer customerId);




}
