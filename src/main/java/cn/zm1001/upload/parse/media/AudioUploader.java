package cn.zm1001.upload.parse.media;

import cn.zm1001.upload.config.UploaderConfig;
import cn.zm1001.util.common.StringUtils;
import org.apache.commons.fileupload.FileItem;

import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 音频文件上传
 */
public class AudioUploader extends AbstractMediaUploader {
    @Override
    public String saveMedia(UploaderConfig config, Map<String, String> params, Map<String, FileItem> files) {
        final FileItem fileItem = getDefaultFile(files);
        final String fileName = StringUtils.firstNonBlank(params.get("fileName"), fileItem.getName());
        final String savePath = getSavePath(params);
        return write(config, fileItem, savePath, fileName);
    }
}
