package cn.zm1001.upload.parse.img;

import cn.zm1001.upload.config.UploaderConfig;
import cn.zm1001.upload.exception.UploaderException;
import cn.zm1001.upload.parse.BaseUploader;
import cn.zm1001.upload.utils.FileUtils;
import cn.zm1001.upload.utils.ImageUtils;
import cn.zm1001.util.common.StringUtils;
import cn.zm1001.util.common.id.IdUtils;
import org.apache.commons.fileupload.FileItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 图片上传抽象类
 */
public abstract class AbstractImgUploader extends BaseUploader {

    /**
     * 通过图片名获取文件后缀
     *
     * @param imgName 图片名
     * @return 后缀
     */
    private String getSuffix(String imgName) {
        // 文件类型
        final String suffix = FileUtils.getFileType(imgName);
        if (StringUtils.isEmpty(suffix)) {
            throw new UploaderException("未能获取图片类型");
        }
        return suffix;
    }

    /**
     * 保存图片
     *
     * @param config 配置信息
     * @param params 请求参数
     * @param files  上传文件
     * @return 图片地址
     */
    public abstract String saveImg(UploaderConfig config, Map<String, String> params, Map<String, FileItem> files);

    /**
     * 将图片保存至磁盘(保留图片名称)
     *
     * @param config   上传配置
     * @param image    图片数据流
     * @param savePath 保存路径
     * @param imgName  图片名称
     * @return 图片访问地址
     */
    protected String write(UploaderConfig config, BufferedImage image, String savePath, String imgName) {
        return write(config, image, savePath, imgName, true);
    }

    /**
     * 将图片保存至磁盘
     *
     * @param config      上传配置
     * @param image       图片数据流
     * @param savePath    保存路径
     * @param imgName     图片名称
     * @param useRealName 是否使用图片真实名称
     * @return 图片访问地址
     */
    protected String write(UploaderConfig config, BufferedImage image, String savePath, String imgName, boolean useRealName) {
        // 文件类型
        final String suffix = getSuffix(imgName);
        // 保护目录
        final String protectPath = randomPath();
        final File imgDir = new File(config.getFilePath() + savePath + protectPath);
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }

        if (!FileUtils.isValidFilename(imgName) || !useRealName) {
            imgName = IdUtils.fastNanoId() + "_" + image.getWidth() + "_" + image.getHeight() + "." + suffix;
        }
        try {
            ImageIO.write(image, suffix, new File(imgDir, imgName));
        } catch (IOException e) {
            throw new UploaderException("写入图片失败");
        }
        return config.getFileDomain() + savePath + protectPath + "/" + imgName;
    }

    /**
     * 根据传入图片生成大中小三张图片
     * 大图：800x800
     * 中图：300x300
     * 小图：100x100
     *
     * @param config      上传配置
     * @param image       图片数据流
     * @param savePath    保存路径
     * @param imgName     图片名称
     * @param useRealName 是否使用图片真实名称
     * @return 图片访问地址
     */
    protected String write3Img(UploaderConfig config, BufferedImage image, String savePath, String imgName, boolean useRealName) {
        // 文件类型
        final String suffix = getSuffix(imgName);

        // 保护目录
        final String protectPath = randomPath();

        if (!FileUtils.isValidFilename(imgName) || !useRealName) {
            imgName = IdUtils.fastNanoId() + "_" + image.getWidth() + "_" + image.getHeight() + "." + suffix;
        }

        try {
            // 生成大图
            this.write(image, config.getFilePath() + savePath + "/big" + protectPath, imgName, suffix, 800, 800);
            // 生成中图
            this.write(image, config.getFilePath() + savePath + "/middle" + protectPath, imgName, suffix, 300, 300);
            // 生成小图
            this.write(image, config.getFilePath() + savePath + "/big" + protectPath, imgName, suffix, 100, 100);
        } catch (IOException e) {
            throw new UploaderException("写入图片失败");
        }

        return config.getFileDomain() + savePath + "/middle" + protectPath + "/" + imgName;
    }

    /**
     * 将图片按照固定大写写入磁盘
     *
     * @param image   图片数据流
     * @param imgDir  图片保存文件夹
     * @param imgName 图片名称
     * @param suffix  图片类型
     * @param width   指定宽度
     * @param height  指定高度
     * @throws IOException
     */
    private void write(BufferedImage image, String imgDir, String imgName, String suffix, int width, int height) throws IOException {
        File imgPath = new File(imgDir);
        if (!imgPath.exists()) {
            imgPath.mkdirs();
        }
        BufferedImage img = ImageUtils.thumbnail(image, width, height);
        ImageIO.write(img, suffix, new File(imgPath, imgName));
    }
}
