package cn.zm1001.upload.service;

import cn.zm1001.upload.config.UploaderConfig;
import cn.zm1001.upload.constant.Const.HandlerMediaType;
import cn.zm1001.upload.exception.UploaderException;
import cn.zm1001.upload.parse.media.AudioUploader;
import cn.zm1001.util.common.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 媒体文件上传
 */
@Service
public class MediaServiceImpl implements IUploadService {
    @Override
    public String uploader(UploaderConfig config, Map<String, String> params, Map<String, FileItem> files) {
        HandlerMediaType type = HandlerMediaType.ordinalByName(StringUtils.trim(params.get("type")));
        if (null == type) {
            throw new UploaderException("不支持的分类类型");
        }

        switch (type) {
            case AUDIO:
                return new AudioUploader().saveMedia(config, params, files);
            default:
                throw new UploaderException("不支持的分类类型");
        }
    }
}
