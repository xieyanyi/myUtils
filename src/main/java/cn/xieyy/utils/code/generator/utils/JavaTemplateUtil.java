package cn.xieyy.utils.code.generator.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.xieyy.utils.code.generator.model.Constant;
import cn.xieyy.utils.code.generator.model.Field;

public class JavaTemplateUtil {

	public static void createModel(List<Field> fieldList,String className,String packageName,String tableName) {
		StringBuffer fileSb = ReadWriterUtil.readFileByLines(Constant.templatePath + "\\Model.java");
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsVar##", getFieldsVar(fieldList));
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsConstruction1##", getFieldsConstruction1(fieldList));
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsConstruction2##", getFieldsConstruction2(fieldList));
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsMethod##", getFieldsMethod(fieldList,true));
		StringReplaceUtil.replaceAll(fileSb, "##packageName##", packageName);
		StringReplaceUtil.replaceAll(fileSb, "##className##", className);
		StringReplaceUtil.replaceAll(fileSb, "##tableName##", tableName);
		ReadWriterUtil.writerFile(Constant.srcPath +"\\"+className+".java", fileSb);
		System.out.println("生成代码:"+Constant.srcPath +"\\"+className+".java");
	}
	
	public static void createForm(List<Field> fieldList,String className,String packageName) {
		StringBuffer fileSb = ReadWriterUtil.readFileByLines(Constant.templatePath + "\\ModelForm.java");
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsVar##", getFieldsVar(fieldList));
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsConstruction1##", getFieldsConstruction1(fieldList));
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsConstruction2##", getFieldsConstruction2(fieldList));
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsMethod##", getFieldsMethod(fieldList,false));
		StringReplaceUtil.replaceAll(fileSb, "##packageName##", packageName);
		StringReplaceUtil.replaceAll(fileSb, "##className##", className);
		ReadWriterUtil.writerFile(Constant.srcPath +"\\"+className+"Form.java", fileSb);
		System.out.println("生成代码:"+Constant.srcPath +"\\"+className+"Form.java");
	}

	public static void createDao(List<Field> fieldList,String className,String packageName) {
		StringBuffer fileSb = ReadWriterUtil.readFileByLines(Constant.templatePath + "\\ModelDao.java");
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsDao##", getFieldsDao(fieldList));
		StringReplaceUtil.replaceAll(fileSb, "##packageName##", packageName);
		StringReplaceUtil.replaceAll(fileSb, "##className##", className);
		ReadWriterUtil.writerFile(Constant.srcPath +"\\"+className+"Dao.java", fileSb);
		System.out.println("生成代码:"+Constant.srcPath +"\\"+className+"Dao.java");
	}

	public static void createService(List<Field> fieldList,String className,String packageName,String classNameVar) {
		StringBuffer fileSb = ReadWriterUtil.readFileByLines(Constant.templatePath + "\\ModelService.java");
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsDao##", getFieldsDao(fieldList));
		StringReplaceUtil.replaceAll(fileSb, "##packageName##", packageName);
		StringReplaceUtil.replaceAll(fileSb, "##className##", className);
		StringReplaceUtil.replaceAll(fileSb, "##classNameVar##", classNameVar);
		ReadWriterUtil.writerFile(Constant.srcPath +"\\"+className+"Service.java", fileSb);
		System.out.println("生成代码:"+Constant.srcPath +"\\"+className+"Service.java");
	}
	
	public static void createController(List<Field> fieldList,String className,String packageName,String classNameVar,String urlName) {
		StringBuffer fileSb = ReadWriterUtil.readFileByLines(Constant.templatePath + "\\ModelController.java");
		StringReplaceUtil.replaceAll(fileSb, "##getFieldsDao##", getFieldsDao(fieldList));
		StringReplaceUtil.replaceAll(fileSb, "##packageName##", packageName);
		StringReplaceUtil.replaceAll(fileSb, "##className##", className);
		StringReplaceUtil.replaceAll(fileSb, "##classNameVar##", classNameVar);
		StringReplaceUtil.replaceAll(fileSb, "##urlName##", urlName);
		ReadWriterUtil.writerFile(Constant.srcPath +"\\"+className+"Controller.java", fileSb);
		System.out.println("生成代码:"+Constant.srcPath +"\\"+className+"Controller.java");
	}


	
	private static String getFieldsConstruction1(List<Field> fieldList) {
		StringBuffer fieldsVar = new StringBuffer();
		int index = 0 ;
		for (Field field : fieldList) {
			String javaType = field.getJavaType();
			String name = field.getName();
			String nameVar = CommonUtil.getVar(name);
			String fieldVar = javaType+" "+nameVar+", "  ;
			if(index==fieldList.size()-1) fieldVar = javaType+" "+nameVar ;
			fieldsVar.append(fieldVar);
			if(index%5==4 && index!=fieldList.size()-1) fieldsVar.append("\n\t\t\t");
			index++;
		}
		return fieldsVar.toString();
	}

	private static String getFieldsConstruction2(List<Field> fieldList) {
		StringBuffer fieldsVar = new StringBuffer();
		int index = 0;
		for (Field field : fieldList) {
			String name = field.getName();
			String nameVar = CommonUtil.getVar(name);
			String fieldVar = "		this."+nameVar+" = "+nameVar+";	\n"  ;
			if(index==fieldList.size()-1) fieldVar = "		this."+nameVar+" = "+nameVar+";"  ;
			fieldsVar.append(fieldVar);
			index++;
		}
		return fieldsVar.toString();
	}

	private static String getFieldsDao(List<Field> fieldList) {
		StringBuffer fieldsVar = new StringBuffer();
		int index = 0 ;
		for (Field field : fieldList) {
			String name = field.getName();
			String nameVar = name ;//CommonUtil.getVar(name);
			String fieldVar = nameVar+","  ;
			if(index==fieldList.size()-1) fieldVar = nameVar ;
			fieldsVar.append(fieldVar);
			index++;
		}
		return fieldsVar.toString();
	}
	
	private static String getFieldsVar(List<Field> fieldList) {
		StringBuffer fieldsVar = new StringBuffer();
		for (Field field : fieldList) {
			String javaType = field.getJavaType();
			String remark = field.getRemark();
			String name = field.getName();
			String nameVar = name ;//CommonUtil.getVar(name);
			if(remark!=null) remark = "//"+ remark ;
			else remark = "" ;
			
			if("Date".equals(javaType)){
				fieldsVar.append("\t@JsonFormat(pattern=\"yyyy-MM-dd HH:mm:ss\",locale=\"zh\",timezone=\"GMT+8\")\n");
				fieldsVar.append("\t@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm\")\n");
			}
			String fieldVar = "	private "+javaType+" "+ nameVar +";\t\t\t"+remark +"\n" ;
			fieldsVar.append(fieldVar);
		}
		return fieldsVar.toString();
	}
	
	private static String getFieldsMethod(List<Field> fieldList,Boolean hashColumn) {
		StringBuffer fieldsMethod = new StringBuffer();
		int index = 0;
		for (Field field : fieldList) {
			String javaType = field.getJavaType();
			Boolean nullable = field.getNullable();
			Integer length = field.getLength();
			String name = field.getName();
			String nameVar = name ;//CommonUtil.getVar(name);
			String nameMethod = CommonUtil.getMethod(name);
			String column = "\t@Column(name = \""+name+"\")	\n";
			//if(!nullable) column = "\t@Column(name = \""+name+"\", nullable = false)	\n";
			String getMethod =  "\tpublic "+javaType+" get"+nameMethod+"() {		\n" +
							    "\t    return this."+nameVar+";	\n" +
							    "\t}" ;
			String setMethod =  "\tpublic void set"+nameMethod+"("+javaType+" "+nameVar+") {		\n" +
							    "\t    this."+nameVar+"="+nameVar+";	\n" +
								"\t}" ;
			
			if(hashColumn) fieldsMethod.append(column);
			fieldsMethod.append(getMethod+"\n\n");
			if(index==fieldList.size()-1){
				fieldsMethod.append(setMethod+"\n");
			}else{
				fieldsMethod.append(setMethod+"\n\n");
			}
			index++;
		}
		return fieldsMethod.toString();
	}

}
