package org.xh.cms.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName Permission
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 9:26
 * @ModifyDate 2019/6/14 9:26
 * @Version 1.0
 */
@Entity
@Table(name="t_permission")
@Data
@NoArgsConstructor
public class Permission extends BaseModel{
    @JsonProperty("text")
    private String permissionName;
    @JsonProperty("url")
    private String permissionUrl;
    @JsonIgnore
    @ManyToMany(mappedBy = "permissionSet",fetch = FetchType.EAGER)
    private Set<Role> rolesSet;
    @JsonManagedReference
    @OneToMany(mappedBy = "parentPermission")
    @JsonProperty("children")
    private Set<Permission> childrenPermission;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Permission parentPermission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Permission that = (Permission) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }
}
