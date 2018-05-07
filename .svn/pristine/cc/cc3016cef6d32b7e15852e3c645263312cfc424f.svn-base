package util;

/**
 * 工具异常类
 * @author wangb
 *
 */
@SuppressWarnings("serial")
public class RoamProjectException extends RuntimeException {
	private String exceptionMessage;//异常信息
	
	public RoamProjectException(Exception exception){
		this.exceptionMessage=exception.getMessage();
	}

	public RoamProjectException(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getMessage() {//获取异常信息
		return exceptionMessage;
	}

	@Override
	public String toString() {
		return "RoamProjectException [exceptionMessage=" + exceptionMessage + "]";
	}
}
