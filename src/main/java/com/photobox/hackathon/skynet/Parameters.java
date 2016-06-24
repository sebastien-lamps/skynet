package com.photobox.hackathon.skynet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slamps on 23/06/16.
 */
public class Parameters {
	String mainParameter = "";
	List<String> subParameters = new ArrayList<>();

	public void setMainParameter(String mainParameter) {
		this.mainParameter = mainParameter;
	}

	public void setSubParameters(List<String> subParameters) {
		this.subParameters = subParameters;
	}

	public List<String> getSubParameters() {
		return subParameters;
	}

	public String getMainParameter() {
		return mainParameter;
	}
}
