package com.atsun.coreapi.po;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseUUIDModel extends BaseModel {

    private static final long serialVersionUID = 3298876826749220766L;

    /**
     * 主键: UUID
     */
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 36)
    private String id;

    public String getId() {
        return StrUtil.emptyToNull(this.id);
    }

    public void setId(String id) {
        this.id = StrUtil.emptyToNull(id);
    }

    public BaseUUIDModel(String id) {
        this.id = id;
    }

}
