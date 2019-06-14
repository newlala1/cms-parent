package org.xh.cms.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @ClassName BaseModel
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 8:45
 * @ModifyDate 2019/6/14 8:45
 * @Version 1.0
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseModel {
    @Id
    @GenericGenerator(name="uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(length=32)
    private String id;
}
