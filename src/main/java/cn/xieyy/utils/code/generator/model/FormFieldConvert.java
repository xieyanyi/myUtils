package cn.xieyy.utils.code.generator.model;

import java.util.List;

import cn.xieyy.utils.code.generator.utils.CommonUtil;

public class FormFieldConvert {


	public FormFieldConvert() {
		super();
	}
	
	// eList只能传入1个或2个元素
	public static String toFormStr(List<Field> eList){
		String formRow = "\t\t<div class=\"row-fluid\">\n" ;
		String showSpan = eList.size()==2 ? " span6" :" span12";
		for (Field field : eList) {
			formRow += getNameFormStr(field,showSpan) ;
		}
		formRow += "\t\t</div>\n" ;
		return formRow ;
	}


	public static String getNameFormStr(Field field,String showSpan) {
		String nameVar = CommonUtil.getVar(field.getName());
		Integer length = field.getLength();
		String fieldStr = "" ;
		String labelRequired = "" ;
		String inputRequired = "" ;
		if(!field.getNullable()){
			labelRequired = "<span class=\"red\">*</span>" ;
			inputRequired = "class=\"validate[required]\"" ;
		}
		
		String fieldHidden = "\t\t	<input id=\""+nameVar+"\" name=\""+nameVar+"\" value=\"${form."+nameVar+"}\" />\n";
		
		String fieldText = "\t\t  <div class=\"control-group"+showSpan+"\">\n"+
									"\t\t    <label class=\"control-label\" >"+labelRequired+field.getRemark()+"</label>\n"+
									"\t\t    <input type=\"text\" id=\""+nameVar+"\" name=\""+nameVar+"\" value=\"${form."+nameVar+"}\" "+inputRequired+" maxlength=\""+length+"\" placeholder=\"\" >\n"+
								"\t\t  </div>\n";

		String fieldDate = "\t\t  <div class=\"control-group"+showSpan+"\">\n"+
				"\t\t    <label class=\"control-label\" >"+labelRequired+field.getRemark()+"</label>\n"+
				"\t\t    <input type=\"text\" id=\""+nameVar+"\" name=\""+nameVar+"\" value=\"${form."+nameVar+"}\" "+inputRequired+" maxlength=\""+length+"\" readOnly>\n"+
				"\t\t  </div>\n";

		String fieldSelect = "\t\t  <div class=\"control-group"+showSpan+"\">\n"+
									"\t\t    <label class=\"control-label\">"+labelRequired+field.getRemark()+"</label>\n"+
									"\t\t    <select id=\""+nameVar+"\" name=\""+nameVar+"\" value=\"${form."+nameVar+"}\" ></select>\n"+
								"\t\t  </div>\n";
		
		String fieldRadio = "\t\t  <div class=\"control-group"+showSpan+"\">\n"+
									"\t\t    <label class=\"control-label\">"+labelRequired+field.getRemark()+"</label>\n"+
									"\t\t    <span id=\""+nameVar+"\" name=\""+nameVar+"\" value=\"${form."+nameVar+"}\" class=\"radio\"></span>\n"+
							"\t\t  </div>\n";
		
		String fieldCheckbox = "\t\t  <div class=\"control-group"+showSpan+"\">\n"+
									"\t\t    <label class=\"control-label\">"+labelRequired+field.getRemark()+"</label>\n"+
									"\t\t    <span id=\""+nameVar+"\" name=\""+nameVar+"\" value=\"${form."+nameVar+"}\" class=\"checkbox\"></span>\n"+
							   "\t\t  </div>\n";
		
		String fieldTextarea = "\t\t  <div class=\"control-group span12\" >\n"+
									"\t\t    <label class=\"control-label\">"+labelRequired+field.getRemark()+"</label>\n"+
									"\t\t    <textarea id=\""+nameVar+"\" name=\""+nameVar+"\" maxlength=\""+length+"\" style=\"width:550px;\">${form."+nameVar+"}</textarea>\n"+
								"\t\t  </div>\n";
		switch (field.getType()) {
		case "hidden":
			fieldStr =  fieldHidden ;
			break;
		case "text":
			fieldStr =  fieldText ;
			break;
		case "date":
			fieldStr =  fieldDate ;
			break;
		case "select":
			fieldStr =  fieldSelect ;
			break;
		case "radio":
			fieldStr =  fieldRadio ;
			break;
		case "checkbox":
			fieldStr =  fieldCheckbox ;
			break;
		case "textarea":
			fieldStr =  fieldTextarea ;
			break;
		default:
			break;
		}
		return fieldStr ;
	}
	
}
