package util.fields;

public class DetailExpectFieldForParseJson {
	// {"expDetailJson":[{"tablename":"查询表名","scripturl":"getgsmresult.sh","defaults":"0","fieldNames":"Mob_fee@111^Toll_fee@111^dis_id@111^durat@111"}]}
	private String tablename;
	private String scripturl;
	private String defaults;
	private String fieldNames;

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getScripturl() {
		return scripturl;
	}

	public void setScripturl(String scripturl) {
		this.scripturl = scripturl;
	}

	public String getDefaults() {
		return defaults;
	}

	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

	public String getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String fieldNames) {
		this.fieldNames = fieldNames;
	}

	@Override
	public String toString() {
		return "DetailExpectFieldForParseJson [tablename=" + tablename + ", scripturl=" + scripturl + ", defaults="
				+ defaults + ", fieldNames=" + fieldNames + "]";
	}
}
