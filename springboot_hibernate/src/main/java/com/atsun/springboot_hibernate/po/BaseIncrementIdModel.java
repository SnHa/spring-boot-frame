package com.atsun.springboot_hibernate.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseIncrementIdModel extends BaseModel {

    private static final long serialVersionUID = 3298876826749220766L;

    /**
     * 主键：自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BaseIncrementIdModel(Long id) {
        this.id = id;
    }

}
