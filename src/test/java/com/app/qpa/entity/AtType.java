package com.app.qpa.entity;

import java.util.List;

import com.app.qpa.element.Item;
import com.app.qpa.element.Type;
import lombok.Data;

@Data
public class AtType implements Type{
	private String id;
	private String name;
	private List<AtItem> items;

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
	public List<? extends Item> getItems() {
		return this.items;
	}

}
