package hu.szrnkapeter.rendertheme.model;

public class Param {

	private String type;
	private String value;

	public Param() {
	}

	public Param(String t, String v) {
		type = t;
		value = v;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
