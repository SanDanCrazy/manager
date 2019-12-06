package fscut.manager.demo.entity;

import fscut.manager.demo.entity.UPK.RolePermissionUPK;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "customer_role")
public class CustomerRole {

    @EmbeddedId
    private RolePermissionUPK rolePermissionUPK;
}
