package cn.zm1001.upload.exception;

import lombok.Getter;

/**
 * @author Dongd_Zhou
 * @desc 上传异常
 */
public class UploaderException extends RuntimeException {
    private static final long serialVersionUID = 6994504517300403111L;

    @Getter
    private String tips;

    public UploaderException() {
        super();
    }

    public UploaderException(String message) {
        super(message);
        this.tips = message;
    }

    public UploaderException(String message, Throwable cause) {
        super(message, cause);
        this.tips = message;
    }

    public UploaderException(Throwable cause) {
        super(cause);
    }


}
