package com.atsun.coreapi.enums;

import cn.hutool.core.io.FileUtil;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/7/9</p>
 *
 * @author LD
 */
@Getter
public enum FileType implements EnumFormat<String> {

    UNKNOWN("UNKNOWN", "未知"),
    FLASH("FLASH", "动画"),
    ZIP("ZIP", "压缩文件"),
    DOCUMENT("DOCUMENT", "文档"),
    IMAGE("IMAGE", "图片"),
    AUDIO("AUDIO", "音频"),
    VIDEO("VIDEO", "视频"),
    INSTALL_PACK("INSTALL_PACK", "安装包");

    private final String tag;

    private final String name;

    private int orderNum;

    FileType(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public static final List<String> FILE_TYPE_FLASH_EXTENSIONS = new ArrayList<String>() {

        private static final long serialVersionUID = 2542634769863730753L;

        {
            add("swf");
        }
    };

    public static final List<String> FILE_TYPE_ZIP_EXTENSIONS = new ArrayList<String>() {

        private static final long serialVersionUID = 2542634769863730753L;

        {
            add("rar");
            add("zip");
            add("arj");
            add("7z");
            add("gz");
            add("gzip");
            add("tar");
            add("z");
        }
    };

    public static final List<String> FILE_TYPE_DOCUMENT_EXTENSIONS = new ArrayList<String>() {

        private static final long serialVersionUID = 2542634769863730753L;

        {
            add("txt");
            add("doc");
            add("docx");
            add("hlp");
            add("wps");
            add("rtf");
            add("htm");
            add("html");
            add("pdf");
            add("xls");
            add("xlsx");
            add("ppsx");
        }
    };

    public static final List<String> FILE_TYPE_IMAGE_EXTENSIONS = new ArrayList<String>() {

        private static final long serialVersionUID = 5225934782688041656L;

        {
            add("webp");
            add("bmp");
            add("jpg");
            add("jpeg");
            add("gif");
            add("pic");
            add("png");
            add("tif");
            add("svg");
            add("pcx");
            add("tga");
            add("exif");
            add("fpx");
            add("psd");
            add("cdr");
            add("pcd");
            add("dxf");
            add("ufo");
            add("eps");
            add("ai");
            add("raw");
            add("wmf");
            add("ico");
        }
    };

    public static final List<String> FILE_TYPE_VIDEO_EXTENSIONS = new ArrayList<String>() {

        private static final long serialVersionUID = 5919366488343818857L;

        {
            add("avi");
            add("navi");
            add("wmv");
            add("asf");
            add("asx");
            add("rm");
            add("rmvb");
            add("mpg");
            add("mpeg");
            add("mpe");
            add("dat");
            add("vob");
            add("dv");
            add("3gp");
            add("3g2");
            add("mkv");
            add("mp4");
            add("m4v");
            add("flv");
            add("mov");
            add("mlv");
            add("m2v");
        }
    };

    public static final List<String> FILE_TYPE_AUDIO_EXTENSIONS = new ArrayList<String>() {

        private static final long serialVersionUID = -2623950165858693700L;

        {
            add("wav");
            add("aif");
            add("au");
            add("mp3");
            add("ram");
            add("wma");
            add("mmf");
            add("amr");
            add("aac");
            add("flac");
        }
    };

    public static final List<String> FILE_TYPE_INSTALL_PACK_EXTENSIONS = new ArrayList<String>() {

        private static final long serialVersionUID = -2623950165858693700L;

        {
            add("apk");
            add("wgt");
        }
    };

    public static FileType getEnum(String tag) {
        try {
            return valueOf(tag.toUpperCase());
        } catch (Exception ignore) {
        }

        return UNKNOWN;
    }

    public static FileType getEnumByExtension(String extension) {

        if (StringUtils.isBlank(extension)) {
            return UNKNOWN;
        }

        extension = extension.toLowerCase();

        if (FILE_TYPE_IMAGE_EXTENSIONS.contains(extension)) {
            return IMAGE;
        }

        if (FILE_TYPE_AUDIO_EXTENSIONS.contains(extension)) {
            return AUDIO;
        }

        if (FILE_TYPE_VIDEO_EXTENSIONS.contains(extension)) {
            return VIDEO;
        }

        if (FILE_TYPE_FLASH_EXTENSIONS.contains(extension)) {
            return FLASH;
        }

        if (FILE_TYPE_ZIP_EXTENSIONS.contains(extension)) {
            return ZIP;
        }

        if (FILE_TYPE_DOCUMENT_EXTENSIONS.contains(extension)) {
            return DOCUMENT;
        }

        if (FILE_TYPE_INSTALL_PACK_EXTENSIONS.contains(extension)) {
            return INSTALL_PACK;
        }

        return UNKNOWN;
    }

    public static FileType getEnumByFilename(String filename) {
        return getEnumByExtension(FileUtil.extName(filename));
    }

}
