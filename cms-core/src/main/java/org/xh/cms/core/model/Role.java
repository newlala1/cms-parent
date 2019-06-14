package org.xh.cms.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName Role
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 8:49
 * @ModifyDate 2019/6/14 8:49
 * @Version 1.0
 */
@Entity
@Table(name="t_role")
@Data
@NoArgsConstructor
public class Role extends BaseModel{
    @Column(length = 100)
    private String roleName;
    @JsonIgnore
    @ManyToMany(mappedBy = "roleSet")
    private Set<Admin> adminSet;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "r_role_permission",
            joinColumns = {
                    @JoinColumn(name="role_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="permission_id")
            }
    )
    private Set<Permission> permissionSet;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role role = (Role) o;

        return getId() != null ? getId().equals(role.getId()) : role.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? roleName.hashCode() : 0);
        return result;
    }
}
