package ##packageName##.dao;

import org.springframework.stereotype.Repository;
import com.toone.cost.online.core.amazing.dao.impl.GenericEFDaoImpl;

import ##packageName##.model.##className##;
import ##packageName##.form.##className##Form;

@Repository
public class ##className##Dao extends GenericEFDaoImpl<##className##Form, ##className##> {

	@Override
	protected void initDao() throws Exception {
		setSelectFormHql("new ##packageName##.form.##className##Form(id,##getFieldsDao##)");
		super.initDao();
	}

}
