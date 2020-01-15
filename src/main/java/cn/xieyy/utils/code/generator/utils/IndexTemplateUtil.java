package cn.xieyy.utils.code.generator.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.xieyy.utils.code.generator.model.Constant;
import cn.xieyy.utils.code.generator.model.Field;

public class IndexTemplateUtil {

	public static void createJs(List<Field> fieldList,String urlName) {
		StringBuffer indexJs = ReadWriterUtil.readFileByLines(Constant.templatePath + "\\index.js");
		StringReplaceUtil.replaceAll(indexJs, "##urlName##", urlName);
		//StringReplaceUtil.replaceAll(indexJs, "##getColumnsStr##", getColumnsStr(fieldList));
		//StringReplaceUtil.replaceAll(indexJs, "##getDictFields##", CommonUtil.getDictFields(fieldList));
		ReadWriterUtil.writerFile(Constant.srcPath +"\\index.js", indexJs);
		System.out.println("生成代码:"+Constant.srcPath +"\\index.js");
	}

	public static void createJsp(List<Field> fieldList,String urlName) {
		StringBuffer indexJsp = ReadWriterUtil.readFileByLines(Constant.templatePath + "\\index.jsp");
		StringReplaceUtil.replaceAll(indexJsp, "##urlName##", urlName);
		//StringReplaceUtil.replaceAll(indexJsp, "##getSearchFields##", getSearchFields(fieldList));
		ReadWriterUtil.writerFile(Constant.srcPath +"\\index.jsp", indexJsp);
		System.out.println("生成代码:"+Constant.srcPath +"\\index.jsp");
	}
	
	public static String getFieldSearchFormStr(Field field) {
		String fieldSearchFormStr = "" ;
		String name = CommonUtil.getVar(field.getName());
		String remark = field.getRemark();
		String show = field.getShow();
		String type = field.getType();
		Integer length = field.getLength();
		String dictKey = field.getDictKey();
		
		String text =  "\t\t\t\t  <div class='control-group span4'>\n"+
						"\t\t\t\t    <label class='control-label' >"+remark+"：</label>\n"+
						"\t\t\t\t    <div class='controls'>\n"+
						"\t\t\t\t		<input type='text' id='"+name+"' name='"+name+"' value='' class='' maxlength='"+length+"' placeholder=''>\n"+
						"\t\t\t\t	</div>\n"+
						"\t\t\t\t  </div>\n";
		
		String select = "\t\t\t\t  <div class='control-group span4'>\n"+
							"\t\t\t\t    <label class='control-label' >"+remark+"：</label>\n"+
							"\t\t\t\t    <div class='controls'>\n"+
							"\t\t\t\t		<select id='"+name+"' name='"+name+"' value='' ></select>\n"+
							"\t\t\t\t	</div>\n"+
							"\t\t\t\t  </div>\n";
		
		String radio = "\t\t\t\t  <div class='control-group span4'>\n"+
							"\t\t\t\t    <label class='control-label' >"+remark+"：</label>\n"+
							"\t\t\t\t    <div class='controls'>\n"+
							"\t\t\t\t		<span id='"+name+"' name='"+name+"' value='' class='radio'></span>\n"+
							"\t\t\t\t	</div>\n"+
							"\t\t\t\t  </div>\n";
		
		String checkbox = "\t\t\t\t  <div class='control-group span4'>\n"+
							"\t\t\t\t    <label class='control-label' >"+remark+"：</label>\n"+
							"\t\t\t\t    <div class='controls'>\n"+
							"\t\t\t\t		<span id='"+name+"' name='"+name+"' value=''  class='checkbox'></span>\n"+
							"\t\t\t\t	</div>\n"+
							"\t\t\t\t  </div>\n";
		
		String date = "\t\t\t\t  <div class='control-group span4'>\n"+
							"\t\t\t\t    <label class='control-label' >"+remark+"：</label>\n"+
							"\t\t\t\t    <div class='controls'>\n"+
							"\t\t\t\t		<input type='text' id='"+name+"' name='"+name+"' value='' class='' maxlength='"+length+"' placeholder='' readonly>\n"+
							"\t\t\t\t	</div>\n"+
							"\t\t\t\t  </div>\n";
		
		

		switch (type) {
		case "text":
			fieldSearchFormStr =  text ;
			break;
		case "select":
			fieldSearchFormStr =  select ;
			break;
		case "radio":
			fieldSearchFormStr =  radio ;
			break;
		case "checkbox":
			fieldSearchFormStr =  checkbox ;
			break;
		case "date":
			fieldSearchFormStr =  date ;
			break;
		default:
			break;
		}
		return fieldSearchFormStr ;
	}
	
	private static String getSearchFields(List<Field> fieldList){
		StringBuffer sb = new StringBuffer() ;
		sb.append("<div class=\"row-fluid\">\n");
		for (int i = 0; i < fieldList.size(); i++) {
			Field field = fieldList.get(i);
			/*
			String show = field.getShow();
			if(show.indexOf("S")!=-1){
				if(i==0){
					sb.append("\t\t\t\t<div class=\"row-fluid\">\n");
					sb.append(getFieldSearchFormStr(field));
				}else if(i==fieldList.size()-1){
					sb.append(getFieldSearchFormStr(field));
					sb.append("\t\t\t\t</div>\n");
				}else if (i%3==2) {
					sb.append("\t\t\t\t</div>\n");
					sb.append("\t\t\t\t<div class=\"row-fluid\">\n");
					sb.append(getFieldSearchFormStr(field));
				}else{
					sb.append(getFieldSearchFormStr(field));
				}
				sb.append(getFieldSearchFormStr(field));
				
			}*/
		}
		sb.append("\t\t\t\t</div>");
		return sb.toString() ;
	}
	
	
	private static String getColumnsStr(List<Field> fieldList){
		StringBuffer sb = new StringBuffer() ;
		for (int i = 0; i < fieldList.size(); i++) {
			Field field = fieldList.get(i);
			String name = CommonUtil.getVar(field.getName());
			String title = field.getRemark();
			String type = field.getType();
			String dictKey = field.getDictKey();
			String show = field.getShow();
			/*
			if(show.indexOf("L") != -1){
				if("checkbox".equals(type) || "radio".equals(type) || "select".equals(type)){
					sb.append(" {\n"
							+ "\t		field : '"+name+"',\n"
							+ "\t		align : 'center',\n"
							+ "\t		title : '"+title+"',\n"
							+ "\t		formatter : function(value,row,index){\n"
							+ "\t			return getMultipleValue(cache,'"+dictKey+"');\n"
							+ "\t		}\n"
							+ " \t  	},");
				}else{
					sb.append(" {\n"
							+ "\t		field : '"+name+"',\n"
							+ "\t		align : 'center',\n"
							+ "\t		title : '"+title+"'\n"
							+ "\t  	},");
				}
			}*/
		}
		return sb.toString() ;
	}
}
