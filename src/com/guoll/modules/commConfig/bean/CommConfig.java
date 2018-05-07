package com.guoll.modules.commConfig.bean;

import com.guoll.modules.framework.base.BaseModel;

/**
 * 通用配置
 * 
 * @author lukas 414024003@qq.com
 * @version 1.0
 *
 */
public class CommConfig extends BaseModel{
    private Integer id;

    private Integer provinceID;
    
    private String provinceName;

    private String ipAddress;

    private String hostName;

    private String hostPassWord;

    private String field1;

    private String field2;

    /**
     * 使用标志,具体参照工具类内部枚举类
     */
    private String field3;

    private String field4;

    /**
     * 配置类型
     * 0:脚本,field1路径,field2配置名,field3标志,field4描述
     * 1:文件,field1路径,field2配置名,field3标志,field4描述
     * 2:路径,field1路径,field2配置名,field3标志,field4描述
     */
    private String field5;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(Integer provinceID) {
		this.provinceID = provinceID;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostPassWord() {
		return hostPassWord;
	}

	public void setHostPassWord(String hostPassWord) {
		this.hostPassWord = hostPassWord;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	@Override
	public String toString() {
		return "CommConfig [id=" + id + ", provinceID=" + provinceID + ", provinceName=" + provinceName + ", ipAddress="
				+ ipAddress + ", hostName=" + hostName + ", hostPassWord=" + hostPassWord + ", field1=" + field1
				+ ", field2=" + field2 + ", field3=" + field3 + ", field4=" + field4 + ", field5=" + field5 + "]";
	}

   
    
}