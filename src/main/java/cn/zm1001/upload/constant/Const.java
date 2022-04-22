package cn.zm1001.upload.constant;

import cn.zm1001.util.common.StringUtils;

/**
 * @author Dongd_Zhou
 * @desc 常量
 */
public interface Const {
    /**
     * 接口中指定的图片分类类型，对应不同的处理方式
     */
    enum HandlerImgType {
        /**
         * 图片通用类型，原样上传
         */
        COMMON,
        /**
         * 图片上传后会生成大中小三张图
         */
        IMG3,
        /**
         * 用户头像
         */
        USER_HEAD;

        public static HandlerImgType ordinalByName(String name) {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            for (HandlerImgType type : values()) {
                if (type.name().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * 接口中指定的媒体文件分类类型，对应不同的处理方式
     */
    enum HandlerMediaType {
        /**
         * 音频文件按
         */
        AUDIO;

        public static HandlerMediaType ordinalByName(String name) {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            for (HandlerMediaType type : values()) {
                if (type.name().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * 支持上传的图片类型
     */
    enum ImgType {
        BMP, GIF, JPG, JPEG, PNG;

        public static ImgType ordinalByName(String name) {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            for (ImgType type : values()) {
                if (type.name().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * 支持上传的媒体文件
     */
    enum MediaType {
        // 媒体文件类型
        SWF, FLV, MP3, WAV, WMA, WMV, MPG, ASF, RM, AMR, MID, APE,
        // 视频格式
        MP4, AVI, RMVB;

        public static MediaType ordinalByName(String name) {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            for (MediaType type : values()) {
                if (type.name().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return null;
        }
    }

}
