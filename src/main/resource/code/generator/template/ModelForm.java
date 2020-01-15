package ##packageName##.form;

import java.util.Date;
import com.toone.cost.online.core.amazing.support.AppForm;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ##className##Form extends AppForm {

	private static final long serialVersionUID = 1L;
	
##getFieldsVar##
	
	public ##className##Form() {
		super();
	}

	public ##className##Form(String id) {
		super();
		this.id = id;
	}

	public ##className##Form(String id, ##getFieldsConstruction1##) {
		super();
		this.id = id;
##getFieldsConstruction2##
	}
	
##getFieldsMethod##
}