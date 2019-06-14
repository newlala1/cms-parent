package org.xh.cms.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName Admin
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 8:45
 * @ModifyDate 2019/6/14 8:45
 * @Version 1.0
 */
@Entity
@Table(name="t_admin")
@Data
@NoArgsConstructor
public class Admin extends BaseModel {
    @Column(length = 100)
    private String userName;
    @Column(length = 64)
    private String userPassword;
    @Enumerated
    private UserStatus userStatus;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(
            name="r_admin_role",
            joinColumns = {
                    @JoinColumn(name="admin_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="role_id")
            }

    )
    private Set<Role> roleSet;
    public enum UserStatus{
        ENABLED,
        DISABLED,
        LOCKED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Admin admin = (Admin) o;

        return getId() != null ? getId().equals(admin.getId()) : admin.getId()== null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }
}
