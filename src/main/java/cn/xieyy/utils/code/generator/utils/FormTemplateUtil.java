package cn.xieyy.utils.code.generator.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.xieyy.utils.code.generator.model.Constant;
import cn.xieyy.utils.code.generator.model.Field;

public class FormTemplateUtil {

	
	public static void updateFormJsp(String urlName,String getFormFields) {
		StringBuffer formJsp = ReadWriterUtil.readFileByLines(Constant.srcPath + "\\toEdit.jsp");
		StringReplaceUtil.replaceAll(formJsp, "##urlName##", urlName);
		StringReplaceUtil.replaceAll(formJsp, "##getFormFields##", getFormFields);
		ReadWriterUtil.writerFile(Constant.srcPath +"\\toEdit.jsp", formJsp);
		System.out.println("更新->生成代码:"+Constant.srcPath +"\\toEdit.js");
	}
	
	public static void createJs(List<Field> fieldList,String urlName) {
		StringBuffer formJs = ReadWriterUtil.readFileByLines(Constant.templatePath + "\\toEdit.js");
		StringReplaceUtil.replaceAll(formJs, "##urlName##", urlName);
		//StringReplaceUtil.replaceAll(formJs, "##getDictFields##", CommonUtil.getDictFields(fieldList));
		ReadWriterUtil.writerFile(Constant.srcPath +"\\toEdit.js", formJs);
		System.out.println("生成代码:"+Constant.srcPath +"\\toEdit.js");
	}

	public static void createJsp(List<Field> fieldList,String urlName) {
		StringBuffer formJsp = ReadWriterUtil.readFileByLines(Constant.templatePath + "\\toEdit.jsp");
		StringReplaceUtil.replaceAll(formJsp, "##urlName##", urlName);
		if(fieldList!=null){
			StringBuffer formFields = new StringBuffer();
			for (Field field : fieldList) {
				String fieldName = field.getName();
				String javaType = field.getJavaType();
				if("Date".equals(javaType)){
					formFields.append("  \t\t\t<input type=\"hidden\" id=\""+fieldName+"\" name=\""+fieldName+"\" value=\"<fmt:formatDate value=\"${form."+fieldName+"}\" type=\"date\" pattern=\"yyyy-MM-dd HH:mm:ss\"/>\" />\n");
				}else{
					formFields.append("  \t\t\t<input type=\"hidden\" id=\""+fieldName+"\" name=\""+fieldName+"\" value=\"${form."+fieldName+"}\" />\n");
				}
			}
			StringReplaceUtil.replaceAll(formJsp, "##formFields##", formFields.toString());
		}
		ReadWriterUtil.writerFile(Constant.srcPath +"\\toEdit.jsp", formJsp);
		System.out.println("生成代码:"+Constant.srcPath +"\\toEdit.jsp");
	}
	
}
