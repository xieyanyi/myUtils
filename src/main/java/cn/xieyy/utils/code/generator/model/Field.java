package cn.xieyy.utils.code.generator.model;

public class Field {
	private String name ;			// 字段名称
	private String remark ;			// 备注、注释、控件名称
	private String javaType ;		// String,Double,Integer,Date
	private String type ;			// hidden,text,radio,select,checkbox,date
	private Integer length ;		// 控件最大输入
	private Boolean nullable ;		// 是否允许为null,	[false:不允许,true:允许]
	private String dictKey ;		// 字典key
	private String show ;			// 显示 LFS（L：列表，F：表单，S查询框）
	
	
	public Field() {
		
	}
	// 创建index
	public Field( String remark, String name,String javaType) {
		super();
		this.name = name;
		this.remark = remark;
		this.javaType = javaType;
	}
		
	// 创建index
	public Field( String remark, String name,String javaType,String type, String dictKey,String show,Integer length,Boolean nullable) {
		super();
		this.name = name;
		this.remark = remark;
		this.javaType = javaType;
		this.type = type;
		this.dictKey = dictKey;
		this.show = show;
		this.length = length;
		this.nullable = nullable;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Boolean getNullable() {
		return nullable;
	}
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}
	
	
}
