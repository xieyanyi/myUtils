package cn.xieyy.utils.code.generator.utils;

import java.util.List;

import cn.xieyy.utils.code.generator.model.Field;

public class CommonUtil {

	
	public static String getVar(String str) {
		return str ;
		/*
		StringBuffer sbout = new StringBuffer();
		String[] strArr = str.toLowerCase().split("_");
		if(strArr!=null && strArr.length>0){
			for (int j = 0; j < strArr.length; j++) {
				String s = strArr[j];
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < s.length(); i++) {
					if(i==0 && j!=0) {
						sb.append((s.charAt(i)+"").toUpperCase());
					}else{
						sb.append(s.charAt(i)+"");
					}
				}
				sbout.append(sb);
			}
		}
		return sbout.toString();
		*/
	}
	
	public static String getMethod(String str) {
		StringBuffer sbout = new StringBuffer();
		//String[] strArr = str.toLowerCase().split("_");
		//if(strArr!=null && strArr.length>0){
			//for (int j = 0; j < strArr.length; j++) {
				String s = str;
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < s.length(); i++) {
					if(i==0) {
						sb.append((s.charAt(i)+"").toUpperCase());
					}else{
						sb.append(s.charAt(i)+"");
					}
				}
				sbout.append(sb);
			//}
		//}
		return sbout.toString();
	}
	
	public static String getDictFields(List<Field> fieldList){
		StringBuffer sb = new StringBuffer() ;
		StringBuffer initSb = new StringBuffer("cache.initSelectCache('") ;
		for (int i = 0; i < fieldList.size(); i++) {
			Field field = fieldList.get(i);
			String name = CommonUtil.getVar(field.getName());
			String show = field.getShow();
			String type = field.getType();
			String dictKey = field.getDictKey();
			if(show.indexOf("L") != -1){
				if("checkbox".equals(type)){
					initSb.append(dictKey).append(",");
					sb.append("\t	initCheckboxByKey('"+name+"','"+dictKey+"');\n");
				}
				if("radio".equals(type)){
					initSb.append(dictKey).append(",");
					sb.append("\t	initRadioByKey('"+name+"','"+dictKey+"');\n");
				}
				if("select".equals(type)){
					initSb.append(dictKey).append(",");
					sb.append("\t	initSelect2('"+name+"','"+dictKey+"');\n");
				}
				if("date".equals(type)){
					sb.append("\t	$(\"#"+name+"\").datetimepicker({ \n"+
								"\t\t	language : \"zh-CN\",\n"+
								"\t\t	format: \"yyyy-mm-dd\",\n"+
								"\t\t	minView : \"month\",\n"+
								"\t\t	autoclose : true,\n"+
								"\t\t	todayBtn : true\n"+
								"\t	});\n");
				}
			}
		}
		String initStr = initSb.toString() ;
		if(sb.length()>0) initStr = initSb.substring(0, initSb.length()-1);
		return initStr +"');\n" + sb.toString() ;
	}
}
