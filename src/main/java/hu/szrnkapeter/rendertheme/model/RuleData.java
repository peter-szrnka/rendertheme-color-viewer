package hu.szrnkapeter.rendertheme.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleData {

	private String e;
	private List<String> k;
	private Map<String, Param> params;
	private String type; // Area, caption, etc
	private List<String> v;

	public String getE() {
		return e;
	}

	public List<String> getK() {
		return k;
	}

	public Map<String, Param> getParams() {
		if (params == null) {
			params = new HashMap<>();
		}
		return params;
	}

	public String getType() {
		return type;
	}

	public List<String> getV() {
		if (v == null) {
			v = new ArrayList<>();
		}
		return v;
	}

	public void setE(String e) {
		this.e = e;
	}

	public void setK(List<String> k) {
		this.k = k;
	}

	public void setParams(Map<String, Param> params) {
		this.params = params;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setV(List<String> v) {
		this.v = v;
	}
}