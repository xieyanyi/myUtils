package ##packageName##.service;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.toone.cost.online.core.amazing.dao.support.Filter;
import com.toone.cost.online.core.amazing.dao.support.Limit;
import com.toone.cost.online.core.amazing.dao.support.Page;
import com.toone.cost.online.core.amazing.lang.StringUtil;
import com.toone.cost.online.core.amazing.service.impl.AbstractServiceImpl;
import com.toone.cost.online.core.log.TgLoggerFactory;
import ##packageName##.model.##className##;
import ##packageName##.form.##className##Form;
import ##packageName##.dao.##className##Dao;

@Service
public class ##className##Service extends AbstractServiceImpl<##className##Dao, ##className##Form, ##className##> {

	private final Logger logger = TgLoggerFactory.getLogger(this.getClass());

	@Resource
	public ##className##Dao ##classNameVar##Dao;

	@Override
	protected void initManager() throws Exception {
		setDaoClass(##classNameVar##Dao);
	}

}