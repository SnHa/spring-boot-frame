package com.atsun.springboot_hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**用户和角色多对多的关系
 * @author SH
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 用户年龄
     */
    @Column(name = "user_age")
    private Integer userAge;

    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
                joinColumns = {@JoinColumn(name ="sys_user_id",referencedColumnName ="user_id" )},
                inverseJoinColumns ={@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")})
    private Set<Role> roleSe ;
}
