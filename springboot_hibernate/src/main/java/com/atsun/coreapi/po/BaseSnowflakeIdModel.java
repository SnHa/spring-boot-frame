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
public class BaseSnowflakeIdModel extends BaseModel {

    private static final long serialVersionUID = 3298876826749220766L;

    @Id
    @Column(length = 19)
    @GeneratedValue(generator = "snowflakeId")
    @GenericGenerator(name = "snowflakeId", strategy = "com.atsun.coreapi.orm.id.SnowflakeIdGenerator")
    private String id;

    public String getId() {
        return StrUtil.emptyToNull(this.id);
    }

    public void setId(String id) {
        this.id = StrUtil.emptyToNull(id);
    }

    public BaseSnowflakeIdModel(String id) {
        this.id = id;
    }

}
