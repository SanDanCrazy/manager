package fscut.manager.demo.entity.UPK;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CustomerRoleUPK implements Serializable{

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "role_id")
    private Integer roleId;

    public CustomerRoleUPK(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerRoleUPK that = (CustomerRoleUPK) o;

        if (!customerId.equals(that.customerId)) return false;
        return roleId.equals(that.roleId);
    }

    @Override
    public int hashCode() {
        int result = customerId.hashCode();
        result = 31 * result + roleId.hashCode();
        return result;
    }
}
