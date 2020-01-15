package ##packageName##.model;

import javax.persistence.Column;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.toone.cost.online.core.amazing.support.BaseEntity;

@Entity
@Table(name = "##tableName##", catalog = "online")
public class ##className## extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
##getFieldsVar##
	
	public ##className##() {
		super();
	}

	public ##className##(String id) {
		super();
		this.id = id;
	}

	public ##className##(String id, ##getFieldsConstruction1##) {
		super();
		this.id = id;
##getFieldsConstruction2##
	}
	
##getFieldsMethod##
}