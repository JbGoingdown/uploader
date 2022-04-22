package cn.zm1001.upload.service;

import cn.zm1001.upload.config.UploaderConfig;
import cn.zm1001.upload.constant.Const.HandlerImgType;
import cn.zm1001.upload.exception.UploaderException;
import cn.zm1001.upload.parse.img.CommonUploader;
import cn.zm1001.upload.parse.img.Img3Uploader;
import cn.zm1001.upload.parse.img.UserHeaderUploader;
import cn.zm1001.util.common.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 图片上传服务
 */
@Service
public class ImageServiceImpl implements IUploadService {

    /**
     * 图片上传
     *
     * @param config 配置信息
     * @param params 请求参数
     * @param files  上传文件
     * @return 访问地址
     */
    public String uploader(UploaderConfig config, Map<String, String> params, Map<String, FileItem> files) {
        HandlerImgType type = HandlerImgType.ordinalByName(StringUtils.trim(params.get("type")));
        if (null == type) {
            throw new UploaderException("不支持的分类类型");
        }

        switch (type) {
            case COMMON:
                return new CommonUploader().saveImg(config, params, files);
            case IMG3:
                return new Img3Uploader().saveImg(config, params, files);
            case USER_HEAD:
                return new UserHeaderUploader().saveImg(config, params, files);
            default:
                throw new UploaderException("不支持的分类类型");
        }
    }
}
