package fscut.manager.demo.dao;

import fscut.manager.demo.entity.Message;
import fscut.manager.demo.vo.MessageVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into customer_message (message_id, customer_id) select ?1,?2 from DUAL where not exists (select customer_id from customer_message where message_id = ?1 and customer_id = ?2)", nativeQuery = true)
    void addCustomerMessage(@Param("message_id") Integer messageId, @Param("customer_id") Integer customerId);

    @Query(value = "select distinct message_id from customer_message where customer_id = ?1 and checked = 0",nativeQuery = true)
    List<Integer> getUnreadMessageId(@Param("customer_id") Integer customerId);

    @Query(value = "select new fscut.manager.demo.vo.MessageVO(m.messageId, m.storyUPK, m.content, m.createdTime,cm.customerId,cm.checked) from CustomerMessage cm ,Message m where cm.messageId = m.messageId and cm.customerId = :customerId")
    List<MessageVO> getMessageByCustomerId(@Param("customerId") Integer customerId);


    @Modifying
    @Transactional
    @Query(value = "update customer_message set checked = 1 where message_id = ?1 and customer_id = ?2", nativeQuery = true)
    void readMessage(Integer messageId, Integer customerId);

    @Query(value = "select count(*) from  customer_message where customer_id = ?1 and checked = 0", nativeQuery = true)
    Integer getUnreadMessageNum(Integer customerId);

    @Query(value = "select count(*) from  customer_message as cm left join  customer as c on cm.customer_id = c.id where c.username = ?1 and cm.checked = 0", nativeQuery = true)
    Integer getUnreadMessageNum(String username);

    @Modifying
    @Transactional
    @Query(value = "delete from customer_message where message_id = ?1 and customer_id = ?2", nativeQuery = true)
    void deleteCustomerMessage(Integer messageId, Integer customerId);




}
