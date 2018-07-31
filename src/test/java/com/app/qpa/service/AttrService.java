package com.app.qpa.service;

import com.app.qpa.entity.AtAttr;
import com.app.qpa.mapper.BusAttr;
import com.app.qpa.mapper.PropertyConverterSupport;
import com.app.qpa.source.SourceData;
import org.springframework.core.AttributeAccessorSupport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
@Service
public class AttrService extends PropertyConverterSupport {
    @Override
    protected List<AtAttr> getBusAttrs(SourceData data) {
        //属性对照
        List<AtAttr> attrs = new ArrayList<>();
        //
        AtAttr attr1 = new AtAttr();
        attr1.setCode("age");
        attr1.setType("1");//1-字段；2-公式; 3-常数
        attr1.setValue("");
        attr1.setCaption("年龄");
        attr1.setValueType("");
        attrs.add(attr1);

        AtAttr attr2 = new AtAttr();
        attr2.setCode("income");
        attr2.setType("1");//1-字段；2-公式; 3-常数
        attr2.setValue("");
        attr2.setCaption("收入");
        attr2.setValueType("");
        attrs.add(attr2);

        AtAttr attr3 = new AtAttr();
        attr3.setCode("payMonth");
        attr3.setType("1");//1-字段；2-公式; 3-常数
        attr3.setValue("");
        attr3.setCaption("连续缴交月");
        attr3.setValueType("");
        attrs.add(attr3);

        AtAttr attr4 = new AtAttr();
        attr4.setCode("loan");
        attr4.setType("3");//1-字段；2-公式; 3-常数
        attr4.setValue("");
        attr4.setCaption("缴存金额");
        attr4.setValueType("");
        attrs.add(attr4);

        AtAttr attr5 = new AtAttr();
        attr5.setCode("limitYear");
        attr5.setType("1");//1-字段；2-公式; 3-常数
        attr5.setValue("");
        attr5.setCaption("贷款限制年限");
        attr5.setValueType("");
        attrs.add(attr5);

        AtAttr attr6 = new AtAttr();
        attr6.setCode("loanYear");
        attr6.setType("1");//1-字段；2-公式; 3-常数
        attr6.setValue("");
        attr6.setCaption("贷款年限");
        attr6.setValueType("");
        attrs.add(attr6);

        AtAttr attr7 = new AtAttr();
        attr7.setCode("dknx1");
        attr7.setType("2");//1-字段；2-公式; 3-常数
        attr7.setValue("limitYear+3");
        attr7.setCaption("贷款年限1");
        attr7.setValueType("");
        attrs.add(attr7);

        AtAttr attr8 = new AtAttr();
        attr8.setCode("dknx2");
        attr8.setType("3");//1-字段；2-公式; 3-常数
        attr8.setValue("23");
        attr8.setCaption("贷款年限2");
        attr8.setValueType("");

        AtAttr attr9 = new AtAttr();
        attr9.setCode("adate");
        attr9.setType("3");//1-字段；2-公式; 3-常数
        attr9.setValue("2017-12-12");
        attr9.setCaption("a日期");
        attr9.setValueType("03");
        attrs.add(attr9);

        AtAttr attr10 = new AtAttr();
        attr10.setCode("cdate");
        attr10.setType("1");//1-字段；2-公式; 3-常数
        attr10.setValue("");
        attr10.setCaption("c日期");
        attr10.setValueType("03");

        attrs.add(attr10);

        return attrs;
    }
}
