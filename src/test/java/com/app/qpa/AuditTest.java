package com.app.qpa;

import com.app.qpa.core.AuditEngine;
import com.app.qpa.entity.*;
import com.app.qpa.source.SourceData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class AuditTest {
	private static Logger logger = LoggerFactory.getLogger(AuditTest.class);
	@Autowired
	private AuditEngine auditEngine;

	//稽核数据
	public static List<SourceData> datas = new ArrayList<SourceData>();
	//稽核项
	public static List<AtType> types = new ArrayList<AtType>();

	static{
		logger.info("稽核数据源生成开始");
		for(int i=0;i<10;i++) {
			ServiceData data = new ServiceData();
			data.setId(i+"");
			data.setName("name"+i);
			data.setAge((int) DataGen.between(22, 45));
			data.setIncome(DataGen.between(3200, 10000));
			data.setLoan(DataGen.between(200000, 500000));
			data.setPayMonth((int)DataGen.between(3, 20));
			data.setLimitYear(20);
			data.setLoanYear(DataGen.wapperInt(15, 30));
			data.setCdate("2017-"+DataGen.month());
			datas.add(data);
			logger.info(data.toString());
		}
		logger.info("稽核数据源生成完成!");

		logger.info("开始生成稽核类型：");
		for(int x=0; x < 2; x++){
			AtType type = new AtType();
			type.setId(x+"");
			type.setName("type"+x);
			logger.info(">>稽核类型{}",type);
			List<AtItem> items = new ArrayList<AtItem>();
			for(int i=0; i < 1; i++) {
				AtItem item = new AtItem();
				item.setId(""+x+i);
				item.setName(type.getName()+ "item"+i);
				item.setDescribe(type.getName()+"item"+i+"描述");
				item.setStartDate("2017-"+DataGen.month());
				item.setEndDate("2018-"+DataGen.month());
				logger.info(">>>>稽核项{}：",item);
				List<AtRule> rules = new ArrayList<AtRule>();

				// targetType: 01 常量 ; 02 属性; 03 bool表达式(代表该规则的结果);04 表达式（代表target的值）; 05 正则表达式
				//年龄小于30
				AtRule rule1 = new AtRule();
				rule1.setId(""+x+i+1);
				rule1.setName(item.getName()+ "rule"+1);
				rule1.setSource("age");
				rule1.setOperate("<");
				rule1.setTarget("30");
				rule1.setTargetType("01");
				logger.info(">>>>>>稽核规则{}",rule1);
				System.out.println("rule : "+ rule1.getSource() + rule1.getOperate() +rule1.getTarget());
		//		rules.add(rule1);

				//  loanYear<limitYear
				AtRule rule2 = new AtRule();
				rule2.setId(""+x+i+2);
				rule2.setName(item.getName()+ "rule"+2);
				rule2.setSource("loanYear");
				rule2.setOperate("<");
				rule2.setTarget("limitYear");
				rule2.setTargetType("02");
				logger.info(">>>>>>稽核规则{}",rule2);
				System.out.println("rule : "+ rule2.getSource() + rule2.getOperate() +rule2.getTarget());
		//		rules.add(rule2);

				AtRule rule3 = new AtRule();
				rule3.setId(""+x+i+3);
				rule3.setName(item.getName()+ "rule"+2);
				rule3.setSource("loanYear");
				rule3.setOperate("<");
				rule3.setTarget("dknx1"); // dknx1、dknx2
				rule3.setTargetType("02");
				logger.info(">>>>>>稽核规则{}",rule3);
				System.out.println("rule : "+ rule3.getSource() + rule3.getOperate() +rule3.getTarget());
		//		rules.add(rule3);

				AtRule rule4 = new AtRule();
				rule4.setId(""+x+i+4);
				rule4.setName(item.getName()+ "rule"+4);
				rule4.setSource("loanYear");
				rule4.setOperate("<");
				rule4.setTarget("limitYear+3");
				rule4.setTargetType("04");
				logger.info(">>>>>>稽核规则{}",rule4);
				System.out.println("rule : "+ rule4.getSource() + rule4.getOperate() +rule4.getTarget());
		//		rules.add(rule4);

				AtRule rule5 = new AtRule();
				rule5.setId(""+x+i+5);
				rule5.setName(item.getName()+ "rule"+5);
				rule5.setSource(null);
				rule5.setOperate("<");
				rule5.setTarget("age+5<40");
				rule5.setTargetType("03");
				logger.info(">>>>>>稽核规则{}",rule5);
				System.out.println("rule : "+ rule5.getSource() + rule5.getOperate() +rule5.getTarget());
		//		rules.add(rule5);

				AtRule rule6 = new AtRule();
				rule6.setId(""+x+i+6);
				rule6.setName(item.getName()+ "rule"+6);
				rule6.setSource("cdate");
				rule6.setOperate("<");
				rule6.setTarget("adate+100");
				rule6.setTargetType("04");
				logger.info(">>>>>>稽核规则{}",rule6);
				System.out.println("rule : "+ rule6.getSource() + rule6.getOperate() +rule6.getTarget());
				rules.add(rule6);

				item.setRules(rules );
				items.add(item);
			}
			type.setItems(items);
			types.add(type);
		}

	}

	@Test
	public void test(){
		auditEngine.audit(types,datas);
	}


	private static String prop(int i,int max) {
		String [] props = {"age","income","loan","payMonth","loanYean"};	
		// 0~1 ->0~4
		int index =  DataGen.wapper(0, props.length, 1);
		return props[index];
	}
	
	private static String operate() {
		String [] opts = {">","<","="};
		int index = DataGen.wapper(1, 100, 1)%3;
		return opts[index];
	}
	
	private static class DataGen{
		static double between(double min,double max) {
			return Math.floor(min + Math.random()*(max - min));
		}
		static int wapperInt(int min,int max) {
			return (int)Math.floor(min + Math.random()*(max - min));
		}
		static int wapper(double min,double max,int x) {//10 100 1000
			return (int) between(min,max)/x*x;
		}
		static double fixDouble(double val,int i) {
			return Double.valueOf( String.format("%."+i+"f", val) );
		}
		static String month(){
			int month = (int)(Math.random()*11+1);
			int day = (int)(Math.random()*29+1);
			return String.format("%02d",month)+"-"+String.format("%02d",day);
		}
	}
}
