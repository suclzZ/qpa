package com.app.qpa.entity;

import com.app.qpa.source.AbstractSourceData;
import lombok.Data;

@Data
public class ServiceData extends AbstractSourceData{

	@Override
	public String sourceId() {
		return this.id;
	}

	@Override
	public Class<ServiceData> getBeanClass() {
		return ServiceData.class;
	}

	private String id;
	private String name;
	private int age;
	private double income;
	private double loan;
	private int loanYear;
	private int limitYear;
	private int payMonth;
	private String cdate;

	
}
