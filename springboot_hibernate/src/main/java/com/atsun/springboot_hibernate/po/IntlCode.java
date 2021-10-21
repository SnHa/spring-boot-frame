package com.atsun.springboot_hibernate.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Description: Created by LD on 2019/11/04</p>
 * <p>国际区号</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_intl_code")
public class IntlCode extends BaseIncrementIdModel {

    private static final long serialVersionUID = 2495589284002221104L;

    public static final String DEFAULT_AREA_CODE = "+86";

    /**
     * 地区
     */
    @Column(nullable = false, unique = true, length = 32)
    private String area;

    /**
     * 编码
     */
    @Column(nullable = false, length = 8)
    private String code = DEFAULT_AREA_CODE;

    /**
     * 是否启用
     */
    @Column(nullable = false, columnDefinition = "varchar(1) default 'N' ")
    @Type(type = "yes_no")
    private boolean enable = true;

    /**
     * 排序序号
     */
    @Column(nullable = false)
    private int orderNum = 0;

    public String genFullPhoneNo(String phoneNo) {
        return String.format("%s%s", this.code, phoneNo);
    }

    public boolean isIntl() {
        return isIntl(this.code);
    }

    public static boolean isIntl(String areaCode) {
        return !DEFAULT_AREA_CODE.equals(areaCode);
    }

}
