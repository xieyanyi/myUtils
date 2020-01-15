package cn.xieyy.utils.code.generator.utils;


/**
 * 字符串替换处理类
 * @author 谢燕益
 */
public class StringReplaceUtil {
	
	/**
	 * 使用newStr替换StringBuffer中的oldStr
	 * @param sb
	 * @param oldStr
	 * @param newStr
	 * @return
	 */
	public static StringBuffer replaceAll(StringBuffer sb, String oldStr, String newStr) {
        int i = sb.indexOf(oldStr);
        int oldLen = oldStr.length();
        int newLen = newStr.length();
        while (i > -1) {
            sb.delete(i, i + oldLen);
            sb.insert(i, newStr);
            i = sb.indexOf(oldStr, i + newLen);
        }
        return sb;
    }
	
	/**
	 * 根据别名从sql语句中查找别名对应的真实字段
	 * @param field
	 * @param sqlstr
	 * @return
	 */
	public static String changeTableAliasToRealField(String field,String sqlstr){
		String tableAlias = "";
		if(sqlstr.indexOf(" "+field) != -1){
			String str = sqlstr.substring(0,sqlstr.indexOf(" "+field)).trim();
			if(str.lastIndexOf(" ") != -1){
				String strTmep = str.substring(str.lastIndexOf(" ")+1);
				tableAlias = strTmep;
			}
			if(str.lastIndexOf(",") != -1){
				String strTmep = str.substring(str.lastIndexOf(",")+1);
				tableAlias = strTmep;
			}
		}else if(sqlstr.indexOf("."+field) != -1){
			field = field.trim();
			String str = sqlstr.substring(0,sqlstr.indexOf("."+field) + 1).trim() + field;
			if(str.lastIndexOf(" ") != -1){
				String strTmep = str.substring(str.lastIndexOf(" ")+1);
				tableAlias = strTmep;
			}
			if(str.lastIndexOf(",") != -1){
				String strTmep = str.substring(str.lastIndexOf(",")+1);
				tableAlias = strTmep;
			}
		}
		return tableAlias.trim();
	}
	
	/**
	 * 根据别名从sql语句中查找别名对应的全部真实字段
	 * @param rowid1
	 * @param sqlstr
	 * @return
	 */
	public static String changeAllTableAliasToRealField(String rowid1,String sqlstr){
		if(rowid1.indexOf(",") != -1){
			StringBuffer sb = new StringBuffer();
			String [] fields = rowid1.split(",");
			for (int i = 0; i < fields.length; i++) {
				String tableAlias = changeTableAliasToRealField(fields[i], sqlstr);
				if(tableAlias != null && !"".equals(tableAlias)){
					//sb.append(tableAlias + "."+ fields[i]+",");
					sb.append(tableAlias + ",");
				}
			}
			String strTemp = sb.toString();
			rowid1 = strTemp.substring(0,strTemp.lastIndexOf(","));
		}else{
			String tableAlias = changeTableAliasToRealField(rowid1, sqlstr);
//			rowid1 = tableAlias + "."+ rowid1;
			rowid1 = tableAlias;
		}
		return rowid1;
	}

}
