package com.atsun.api.po;

import com.atsun.api.enums.AccountState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/7/11</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Account extends BaseIncrementIdModel {

    private static final long serialVersionUID = 6713104499227228432L;

    /**
     * 账户状态
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private AccountState state = AccountState.NORMAL;

    /**
     * 最后登录时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDatetime;

    /**
     * Token版本
     */
    @Column(nullable = false)
    private int tokenVer = 0;

    public Account(Long id) {
        super(id);
    }

    public static String hashPassword(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plaintext, String hashed) {
        return StringUtils.isNotBlank(hashed) && BCrypt.checkpw(plaintext, hashed);
    }

}
