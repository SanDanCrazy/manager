package fscut.manager.demo.entity.UPK;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RolePermissionUPK implements Serializable {

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "permission_id")
    private Integer permissionId;

    public RolePermissionUPK(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePermissionUPK that = (RolePermissionUPK) o;

        if (!roleId.equals(that.roleId)) return false;
        return permissionId.equals(that.permissionId);
    }

    @Override
    public int hashCode() {
        int result = roleId.hashCode();
        result = 31 * result + permissionId.hashCode();
        return result;
    }
}
