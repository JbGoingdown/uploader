package cn.zm1001.upload.exception;

/**
 * @author Dongd_Zhou
 * @desc 上传异常
 */
public class UploaderException extends RuntimeException {
    private static final long serialVersionUID = 6994504517300403111L;

    public UploaderException() {
        super();
    }

    public UploaderException(String message) {
        super(message);
    }

    public UploaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploaderException(Throwable cause) {
        super(cause);
    }
}
