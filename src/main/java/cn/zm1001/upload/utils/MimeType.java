package cn.zm1001.upload.utils;

/**
 * @author Dongd_Zhou
 * @desc 支持文件类型
 */
public interface MimeType {
    String IMAGE_PNG = "image/png";
    String IMAGE_JPG = "image/jpg";
    String IMAGE_JPEG = "image/jpeg";
    String IMAGE_BMP = "image/bmp";
    String IMAGE_GIF = "image/gif";

    String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // 媒体文件类型
            "swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "mpg", "asf", "rm",
            // 视频格式
            "mp4", "avi", "rmvb",
            // pdf
            "pdf",
            // Markdown
            "md"
    };
}
