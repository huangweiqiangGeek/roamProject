package util.fields;

/**
 * 解析免费资源预期结果的数据封装工具类
 * 
 * @author wangb
 * 
 */
public class ResorceExpectFieldForPaseJson {
	// "initVal":"102478","val":"2222","carryVal":"2588","initTextProCarrVal":"122","realityTextProCarrVal":"1222"
	/**
	 * 产品id
	 */
	private String name;
	/**
	 * 类型 0本月 1结转 2本月_结转
	 */
	private String type;
	/**
	 * 初始值
	 */
	private String initVal;
	/**
	 * 预期
	 */
	private String val;
	/**
	 * 类型为2时结转预期
	 */
	private String carryVal;
	/**
	 * 类型为2是结转初始值
	 */
	private String initTextProCarrVal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInitVal() {
		return initVal;
	}

	public void setInitVal(String initVal) {
		this.initVal = initVal;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getCarryVal() {
		return carryVal;
	}

	public void setCarryVal(String carryVal) {
		this.carryVal = carryVal;
	}

	public String getInitTextProCarrVal() {
		return initTextProCarrVal;
	}

	public void setInitTextProCarrVal(String initTextProCarrVal) {
		this.initTextProCarrVal = initTextProCarrVal;
	}

	@Override
	public String toString() {
		return "ResorceExpectFieldForPaseJson [name=" + name + ", type=" + type + ", initVal=" + initVal + ", val="
				+ val + ", carryVal=" + carryVal + ", initTextProCarrVal=" + initTextProCarrVal + "]";
	}

}
