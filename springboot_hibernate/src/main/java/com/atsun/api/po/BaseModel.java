package com.atsun.api.po;

import com.atsun.api.enums.Direction;
import com.atsun.api.utils.CloneBeanUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/1/25</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -7661783613656719177L;

    public static final LinkedHashMap<String, Direction> DEFAULT_ORDER_NUM_ASC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = -7329162908363941022L;

        {
            put("o.orderNum", Direction.ASC);
        }
    };

    public static final LinkedHashMap<String, Direction> DEFAULT_ORDER_NUM_DESC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = -3648208811729096330L;

        {
            put("o.orderNum", Direction.DESC);
        }
    };

    public static final LinkedHashMap<String, Direction> DEFAULT_ORDER_NUM_SQL_ASC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = -6842626669444162089L;

        {
            put("o.order_num", Direction.ASC);
        }
    };

    public static final LinkedHashMap<String, Direction> DEFAULT_ORDER_NUM_SQL_DESC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = -1661411830370141485L;

        {
            put("o.order_num", Direction.DESC);
        }
    };

    public static final LinkedHashMap<String, Direction> DEFAULT_CREATE_DATETIME_ASC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = 8482340838526079166L;

        {
            put("o.createDatetime", Direction.ASC);
        }
    };

    public static final LinkedHashMap<String, Direction> DEFAULT_CREATE_DATETIME_DESC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = -907225480214668866L;

        {
            put("o.createDatetime", Direction.DESC);
        }
    };

    public static final LinkedHashMap<String, Direction> DEFAULT_CREATE_DATETIME_SQL_ASC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = 6203526834359262134L;

        {
            put("o.create_datetime", Direction.ASC);
        }
    };

    public static final LinkedHashMap<String, Direction> DEFAULT_CREATE_DATETIME_SQL_DESC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = -3476741872837045320L;

        {
            put("o.create_datetime", Direction.DESC);
        }
    };

    public static final LinkedHashMap<String, Direction> DEFAULT_UPDATE_DATETIME_ASC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = 6332631214994693919L;

        {
            put("o.updateDatetime", Direction.ASC);
        }
    };

    public static final LinkedHashMap<String, Direction> DEFAULT_UPDATE_DATETIME_DESC_ORDERS = new LinkedHashMap<String, Direction>() {

        private static final long serialVersionUID = -8194784038025797477L;

        {
            put("o.updateDatetime", Direction.DESC);
        }
    };

    /**
     * 创建时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDatetime;

    /**
     * 修改时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDatetime;

    @PrePersist
    public void prePersist() {
        this.createDatetime = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDatetime = new Date();
    }

    public static final List<String> DEFAULT_CLONE_BEAN_IGNORE_PROPERTIES = new ArrayList<String>() {

        private static final long serialVersionUID = 5973066850995392366L;

        {
            add("id");
            add("createDatetime");
            add("updateDatetime");
        }
    };

    protected List<String> cloneBeanIgnoreProperties() {
        return DEFAULT_CLONE_BEAN_IGNORE_PROPERTIES;
    }

    public void modify(Object t) {
        CloneBeanUtils.copyProperties(t, this, null, true, cloneBeanIgnoreProperties().toArray(new String[0]));
    }

}
