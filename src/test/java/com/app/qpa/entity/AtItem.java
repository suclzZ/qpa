package com.app.qpa.entity;

import java.util.List;

import com.app.qpa.element.Item;
import com.app.qpa.element.Rule;
import lombok.Data;

@Data
public class AtItem implements Item{
	private String id;
	private String name;
	private String typeId;
	private String startDate;
	private String endDate;
	private String describe;
	private List<AtRule> rules;
	private String logic;
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getCode() {
		return null;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean valid() {
		return true;
	}

	@Override
	public List<? extends Rule> getRules() {
		return this.rules;
	}

	public void setLogic(String logic){
		if(logic!=null){
			this.logic = "&&";
		}
	}

	@Override
	public String getLoginc() {
		return (logic ==null ||"".equals(logic))?"&&":logic;
	}

}
