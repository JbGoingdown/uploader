package cn.zm1001.upload.parse.media;

import cn.zm1001.upload.config.UploaderConfig;
import cn.zm1001.upload.constant.Const.MediaType;
import cn.zm1001.upload.exception.UploaderException;
import cn.zm1001.upload.parse.BaseUploader;
import cn.zm1001.upload.utils.FileUtils;
import cn.zm1001.util.common.StringUtils;
import cn.zm1001.util.common.id.IdUtils;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 媒体文件抽象类
 */
public abstract class AbstractMediaUploader extends BaseUploader {

    /**
     * 通过媒体文件名获取文件后缀
     *
     * @param mediaName 媒体文件名
     * @return 后缀
     */
    private String getSuffix(String mediaName) {
        // 文件类型
        final String suffix = FileUtils.getFileType(mediaName);
        if (StringUtils.isEmpty(suffix)) {
            throw new UploaderException("未能获取媒体文件类型");
        }
        if (null == MediaType.ordinalByName(suffix)) {
            throw new UploaderException("不支持的媒体文件类型");
        }
        return suffix;
    }

    /**
     * 保存媒体文件
     *
     * @param config 配置信息
     * @param params 请求参数
     * @param files  上传文件
     * @return 媒体文件地址
     */
    public abstract String saveMedia(UploaderConfig config, Map<String, String> params, Map<String, FileItem> files);


    /**
     * 将媒体文件保存至磁盘
     *
     * @param config      上传配置
     * @param fileItem    文件信息
     * @param savePath    保存路径
     * @param mediaName   媒体文件名称
     * @param useRealName 是否使用图片真实名称
     * @return 媒体文件访问地址
     */
    protected String write(UploaderConfig config, FileItem fileItem, String savePath, String mediaName, boolean useRealName) {
        // 文件后缀
        final String suffix = getSuffix(mediaName);
        // 保护目录
        final String protectPath = randomPath();
        final File mediaDir = new File(config.getPath() + savePath + protectPath);
        if (!mediaDir.exists()) {
            mediaDir.mkdirs();
        }
        if (!useRealName) {
            mediaName = IdUtils.fastNanoId() + "." + suffix;
        }
        final File file = new File(mediaDir, mediaName);
        try {
            fileItem.write(file);
        } catch (Exception e) {
            throw new UploaderException("写入媒体文件败");
        }
        return config.getDomain() + savePath + protectPath + "/" + mediaName;
    }
}
