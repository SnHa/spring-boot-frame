package com.atsun.coreapi.po;

import com.atsun.coreapi.enums.AttOrigin;
import com.atsun.coreapi.enums.AttType;
import com.atsun.coreapi.enums.FileType;
import com.atsun.coreapi.enums.StorageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Description: Created by LD on 2019/11/04</p>
 * <p>附件表</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_att")
public class Att extends BaseSnowflakeIdModel {

    private static final long serialVersionUID = 4839137214041518111L;

    /**
     * 文件名
     */
    @Column(nullable = false)
    private String filename;

    /**
     * 原文件名
     */
    @Column(nullable = false)
    private String origFilename;

    /**
     * 附件来源
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private AttOrigin origin = AttOrigin.ADMIN;

    /**
     * 附件类型
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private AttType type;

    /**
     * 文件类型
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FileType fileType = FileType.UNKNOWN;

    /**
     * 存储类型
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    /**
     * 大小
     */
    @Column(nullable = false)
    private Long size;

    /**
     * Hash值(Md5加密)
     */
    @Column(nullable = false, length = 64)
    private String hashVal;

    /**
     * 本地文件路径
     */
    @Column
    private String filePath;

    /**
     * 资源Key
     */
    @Column(unique = true)
    private String fileKey;

    /**
     * 资源路径
     */
    @Column(nullable = false)
    private String addr;

    /**
     * 访问路径
     */
    @Column(nullable = false)
    private String url;

    /**
     * 是否删除
     */
    @Column(nullable = false, columnDefinition = "varchar(1) default 'N' ")
    @Type(type = "yes_no")
    private boolean deleted = false;

    public static String genUrl(String addr) {
        return StringUtils.isBlank(addr) ?
                null : String.format("%s%s", StringUtils.defaultString(System.getProperty("resource.server.att.domain")), StringUtils.defaultString(addr));
    }

    public Att(String id) {
        super(id);
    }

    public Att(String filename, String origFilename, AttOrigin origin, AttType type, FileType fileType, Long size, String hashVal) {
        this.filename = filename;
        this.origFilename = origFilename;
        this.origin = origin;
        this.type = type;
        this.fileType = fileType;
        this.size = size;
        this.hashVal = hashVal;
    }

}
