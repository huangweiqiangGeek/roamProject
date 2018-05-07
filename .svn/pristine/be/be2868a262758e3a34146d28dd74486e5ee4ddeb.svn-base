package util.fields;

/**
 * 模板字段累
 * 
 * @author Administrator
 *
 */
public class BillTemplateAttribute {
	//{“fieldName“:“CALL_DURATION“,“choiceAway“:“20“,“hide“:“否“,“range“:““,“scriptUrl“:““,“explain“:“通话时长“,“default“:““}
	private String Default;
	private String fieldName;
	private String choiceAway;
	private String hide;
	private String range;
	private String scriptUrl;
	private String explain;
	
	public String getDefault() {
		return Default;
	}
	public void setDefault(String default1) {
		Default = default1;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getChoiceAway() {
		return choiceAway;
	}
	public void setChoiceAway(String choiceAway) {
		this.choiceAway = choiceAway;
	}
	public String getHide() {
		return hide;
	}
	public void setHide(String hide) {
		this.hide = hide;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getScriptUrl() {
		return scriptUrl;
	}
	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	@Override
	public String toString() {
		String json ="{\"name\":\""+fieldName+
				"\",\"choiceaway\":\""+choiceAway+
				"\",\"val\":\""+Default+
				"\",\"hide\":\""+hide+
				"\",\"explain\":\""+explain+
				"\",\"default\":\""+Default+"\"}";
		return  json;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Default == null) ? 0 : Default.hashCode());
		result = prime * result + ((choiceAway == null) ? 0 : choiceAway.hashCode());
		result = prime * result + ((explain == null) ? 0 : explain.hashCode());
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((hide == null) ? 0 : hide.hashCode());
		result = prime * result + ((range == null) ? 0 : range.hashCode());
		result = prime * result + ((scriptUrl == null) ? 0 : scriptUrl.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillTemplateAttribute other = (BillTemplateAttribute) obj;
		if (Default == null) {
			if (other.Default != null)
				return false;
		} else if (!Default.equals(other.Default))
			return false;
		if (choiceAway == null) {
			if (other.choiceAway != null)
				return false;
		} else if (!choiceAway.equals(other.choiceAway))
			return false;
		if (explain == null) {
			if (other.explain != null)
				return false;
		} else if (!explain.equals(other.explain))
			return false;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (hide == null) {
			if (other.hide != null)
				return false;
		} else if (!hide.equals(other.hide))
			return false;
		if (range == null) {
			if (other.range != null)
				return false;
		} else if (!range.equals(other.range))
			return false;
		if (scriptUrl == null) {
			if (other.scriptUrl != null)
				return false;
		} else if (!scriptUrl.equals(other.scriptUrl))
			return false;
		return true;
	}
	
	
}
