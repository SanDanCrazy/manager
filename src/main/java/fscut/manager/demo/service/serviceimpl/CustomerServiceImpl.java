package fscut.manager.demo.service.serviceimpl;

import fscut.manager.demo.dao.CustomerRepository;
import fscut.manager.demo.dao.CustomerRoleRepository;
import fscut.manager.demo.dto.CustomerDTO;
import fscut.manager.demo.entity.Customer;
import fscut.manager.demo.entity.CustomerRole;
import fscut.manager.demo.entity.UPK.CustomerRoleUPK;
import fscut.manager.demo.exception.CustomerAlreadyExitsException;
import fscut.manager.demo.exception.CustomerNotExitsException;
import fscut.manager.demo.service.CustomerService;
import fscut.manager.demo.vo.CustomerAuthVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerRoleRepository customerRoleRepository;

    @Resource
    private CustomerRepository customerRepository;

    @Override
    public CustomerRole addToProduct(CustomerAuthVO customerAuthVO){
        CustomerRole customerRole = new CustomerRole();

        customerRole.getCustomerRoleUPK().setProductId(customerAuthVO.getProductId());
        customerRole.getCustomerRoleUPK().setCustomerId(customerRepository.getIdByUsername(customerAuthVO.getUsername()));
        customerRole.getCustomerRoleUPK().setRoleId(customerRoleRepository.getRoleIdByRoleName(customerAuthVO.getRoleName()));
        return customerRoleRepository.save(customerRole);
    }

    @Override
    public List<Customer> getCustomerListByProductId(Integer productId){
        return customerRepository.findCustomersByProductId(productId);
    }

    @Override
    public Integer deleteFromProduct(Integer customerId, Integer productId){
        return customerRoleRepository.deleteFromProduct(customerId, productId);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer deleteCustomer(String username) throws CustomerNotExitsException{
        if(customerRepository.findCustomerByUsername(username) == null) {
            throw new CustomerNotExitsException("customer not exits");
        }

        Integer customerId = customerRepository.getIdByUsername(username);
        customerRepository.deleteCustomerByUsername(username);
        return customerRoleRepository.deleteRoleByCustomerId(customerId);
    }

    @Override
    public void deleteRole(CustomerRole customerRole){
        customerRoleRepository.delete(customerRole);
    }

    @Override
    public void assignRole(CustomerRole customerRole){
        customerRoleRepository.save(customerRole);
    }

    @Override
    public String getUsernameById(Integer userId) {
        if(userId == null){
            return null;
        }
        return customerRepository.getUsernameById(userId);
    }

    @Override
    public String getRealnameById(Integer userId) {
        if(userId == null){
            return null;
        }
        return customerRepository.findRealNameByCustomerId(userId);
    }

    @Override
    public Integer getIdByUsername(String username) {
        return customerRepository.getIdByUsername(username);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public String getRoleCodeByUserId(Integer userId) {
        return customerRepository.findRoleCodeByUserId(userId);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Optional<Customer> createCustomer(CustomerDTO customerDTO) throws CustomerAlreadyExitsException {
        if(customerRepository.findCustomerByUsername(customerDTO.getUsername()) != null) {
            throw new CustomerAlreadyExitsException("customer exits");
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        Customer newCustomer = customerRepository.save(customer);
        return customerRepository.findById(newCustomer.getId());
    }

    @Override
    public Integer updateCustomer(String password, String username) {
        Integer userId = customerRepository.getIdByUsername(username);
        return customerRepository.updateCustomerPassword(password, userId);
    }

    @Override
    public List<String> getAdmins() {
        List<String> adminList = new ArrayList<>();
        List<Integer> adminIds = customerRoleRepository.findAllAdmins();
        for (Integer adminId : adminIds) {
            adminList.add(customerRepository.getUsernameById(adminId));
        }
        return adminList;
    }

    @Override
    public Integer updateCustomerRole(CustomerAuthVO customerAuthVO) {
        Integer productId = customerAuthVO.getProductId();
        Integer roleId = customerRoleRepository.getRoleIdByRoleName(customerAuthVO.getRoleName());
        Integer customerId = customerRepository.getIdByUsername(customerAuthVO.getUsername());
        return customerRoleRepository.updateCustomerRole(roleId, customerId, productId);
    }

    @Override
    public CustomerRoleUPK getCustomerRoleByCusomerIdAndProductId(CustomerAuthVO customerAuthVO) {
        Integer productId = customerAuthVO.getProductId();
        Integer customerId = customerRepository.getIdByUsername(customerAuthVO.getUsername());
        return customerRoleRepository.findByCustomerIdAndProductId(customerId, productId);
    }

}
