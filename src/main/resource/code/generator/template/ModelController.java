package ##packageName##.web;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toone.cost.online.core.amazing.dao.support.Filter;
import com.toone.cost.online.core.amazing.dao.support.Limit;
import com.toone.cost.online.core.amazing.dao.support.Page;
import com.toone.cost.online.core.amazing.dao.support.Filter.Operator;
import com.toone.cost.online.core.amazing.dao.support.Limit.SortOrder;
import com.toone.cost.online.core.amazing.lang.StringUtil;
import com.toone.cost.online.core.amazing.support.JsResultForm;
import com.toone.cost.online.core.amazing.web.AbstractController;
import com.toone.cost.online.core.amazing.web.GenericController;
import com.toone.cost.online.core.log.TgLoggerFactory;
import ##packageName##.form.##className##Form;
import ##packageName##.service.##className##Service;

@Controller
@RequestMapping("##urlName##")
public class ##className##Controller extends GenericController {

	private final String JSP_DIR = "##urlName##";
	
	@Resource
	private ##className##Service ##classNameVar##Service;

	protected String getJspDir() {
		return JSP_DIR;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(getJspDir() + "/index");
		return mav;
	}
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public Page<##className##Form> list(##className##Form form, HttpServletRequest request) {
		Limit limit = getLimit(request);
		/*
		String name = form.getName();
		if(StringUtil.notEmpty(name)) {
			limit.addFilter("name", Operator.CONTAINS, name);
		}
		String searchKey = (String)request.getParameter("searchKey");
		if(StringUtil.notEmpty(searchKey)) {
			limit.addFilter(new Filter(" (name like '%"+searchKey+"%' or title like '%"+searchKey+"%' ) and 1 ", 1, false));
		}
		*/
		Page<##className##Form> page = ##classNameVar##Service.getFormPage(limit);
		return page;
	}

	@RequestMapping(value = "/toView")
	public ModelAndView toView(##className##Form form, HttpServletRequest request){
		ModelAndView mav = new ModelAndView(getJspDir() + "/toView");
		if(form.getId() != null) form = ##classNameVar##Service.getFormById(form.getId());
		mav.addObject("form", form);
		return mav;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(##className##Form form, HttpServletRequest request){
		ModelAndView mav = new ModelAndView(getJspDir() + "/toEdit");
		if(form.getId() != null) form = ##classNameVar##Service.getFormById(form.getId());
		mav.addObject("form", form);
		return mav;
	}
	
	@RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
	public JsResultForm saveUpdate(##className##Form form, HttpServletRequest request) {
		JsResultForm result = new JsResultForm();
		Boolean success = false;
		try {
			if(StringUtil.empty(form.getId())){
				success = ##classNameVar##Service.save(form);
			}else{
				success = ##classNameVar##Service.update(form);
			}
		} catch (Exception e) {
			result.setMsg(e.getMessage());
		}
		result.setSuccess(success);
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsResultForm delete(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request) {
		JsResultForm result = new JsResultForm();
		try {
			Limit limit = new Limit();
			limit.addFilter("id", Operator.IS_IN, Arrays.asList(ids));
			##classNameVar##Service.deleteByLimit(limit);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

}
