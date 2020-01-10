package fscut.manager.demo.dao;

import fscut.manager.demo.entity.CustomerRole;
import fscut.manager.demo.entity.UPK.CustomerRoleUPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerRoleRepository extends JpaRepository<CustomerRole, CustomerRoleUPK> {

    @Query(value = "select id from role where role_name = ?1", nativeQuery = true)
    Integer getRoleIdByRoleName( String roleName);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "delete from customer_role where customer_id = ?1 and product_id = ?2", nativeQuery = true)
    Integer deleteFromProduct(Integer customerId, Integer productId);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "delete from customer_role where customer_id = ?1", nativeQuery = true)
    void deleteRoleByCustomerId(Integer customerId);

    @Query(value = "select distinct customer_id from customer_role where role_id = 6", nativeQuery = true)
    List<Integer> findAllAdmins();

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "update CustomerRole set role_id = ?1 where customer_id = ?2 and product_id = ?3")
    Integer updateCustomerRole(Integer roleId, Integer customerId, Integer productId);
}
