package cn.xieyy.utils.code.generator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.xieyy.utils.code.generator.model.Field;
import cn.xieyy.utils.code.generator.model.FormFieldConvert;
import cn.xieyy.utils.code.generator.utils.FormTemplateUtil;
import cn.xieyy.utils.code.generator.utils.IndexTemplateUtil;
import cn.xieyy.utils.code.generator.utils.JavaTemplateUtil;

public class GeneratorTest {
	
	@Test
	public void generatorCode() {
		String packageName = "com.toone.cost.online.cms.quest" ;
		String className = "QuestUser" ;		
		String classNameVar = "questUser" ;		// 类名变量（一般为类名第一个字母小写）
		String tableName = "cost_cms_quest_user" ;
		String urlName = "quest/user" ;			// Controller的URL
		
		List<Field> fieldList = new ArrayList<Field>();
		fieldList.add(new Field("登录用户Id","loginUserId",  "String"));
		fieldList.add(new Field("姓名","uname",  "String"));
		fieldList.add(new Field("省份","province", "String"));
		fieldList.add(new Field("城市","city", "String"));
		fieldList.add(new Field("单位名称","org", "String"));
		fieldList.add(new Field("邮寄地址","address", "String"));
		fieldList.add(new Field("邮箱","email", "String"));
		fieldList.add(new Field("电话","phone", "String"));
		fieldList.add(new Field("修改时间","modifyTime", "String"));
		fieldList.add(new Field("中奖情况","zjqk",  "String"));
		fieldList.add(new Field("抽奖时间","cjTime",  "String"));


		// 生成java
		JavaTemplateUtil.createModel(fieldList,className,packageName,tableName);
		JavaTemplateUtil.createForm(fieldList,className,packageName);
		JavaTemplateUtil.createDao(fieldList,className,packageName);
		JavaTemplateUtil.createService(fieldList,className,packageName,classNameVar);
		JavaTemplateUtil.createController(fieldList,className,packageName,classNameVar,urlName);
		
		
		IndexTemplateUtil.createJsp(fieldList,urlName);
		IndexTemplateUtil.createJs(fieldList,urlName);
		FormTemplateUtil.createJsp(fieldList,urlName);
		FormTemplateUtil.createJs(fieldList,urlName);
		
		/*
		fieldList.clear();
		StringBuffer formSb = new StringBuffer();
		fieldList.add(new Field("父节点ID","PARENTID",  "String", "text", null, "F", 32, false));
		fieldList.add(new Field("名称","NAME", "String", "text", null, "FLS", 100, false));
		formSb.append(FormFieldConvert.toFormStr(fieldList));
		fieldList.clear();
		fieldList.add(new Field("排序号", "SORT", "String", "text", null, "FL", 32, true));
		fieldList.add(new Field("级别", "GRADE", "String", "text", null, "FL", 32, true));
		formSb.append(FormFieldConvert.toFormStr(fieldList));
		fieldList.clear();
		FormTemplateUtil.updateFormJsp(urlName, formSb.toString());
		*/
	}

}
