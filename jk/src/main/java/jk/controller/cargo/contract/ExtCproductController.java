package jk.controller.cargo.contract;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jk.controller.BaseController;
import jk.domain.ExtCproduct;
import jk.domain.Factory;
import jk.domain.SysCode;
import jk.service.ContractProductService;
import jk.service.ExtCproductService;
import jk.service.FactoryService;

/**
 * @author zhangtai
 *
 */
@Controller
public class ExtCproductController  extends BaseController{

	@Resource 
	ExtCproductService extCproductService;
	@Resource
	FactoryService factroyService;
	
	@RequestMapping("/cargo/extcproduct/tocreate.action")
	public String tocreate(String contractProductId, Model model){
		
		//某个货物下的附件信息
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("contractProductId", contractProductId);
		model.addAttribute("contractProductId", contractProductId);
		
		List<ExtCproduct> dataList = this.extCproductService.find(paraMap);
		model.addAttribute("dataList", dataList);
		
		List<Factory> factoryList = factroyService.getFactoryList();
		model.addAttribute("factoryList", factoryList);
		
		List<SysCode> ctypeList = this.extCproductService.getCtypeList();
		model.addAttribute("ctypeList", ctypeList);
		
		return "/cargo/contract/jExtCproductCreate";
	}
	@RequestMapping("/cargo/extcproduct/insert.action")
	public String insert(ExtCproduct extCproduct,Model model){
		extCproductService.insert(extCproduct);
		model.addAttribute("contractProductId", extCproduct.getContractProductId()); 
		
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
	@RequestMapping("/cargo/extcproduct/toupdate.action")
	public String toupdate(String id, Model model){
		ExtCproduct obj = extCproductService.get(id);
		model.addAttribute("obj", obj);
		//准备生产厂家的下拉列表
		List<Factory> factoryList = factroyService.getFactoryList();
		model.addAttribute("factoryList", factoryList);
		//准备分类下拉列表
		List<SysCode> ctypeList = this.extCproductService.getCtypeList();
		model.addAttribute("ctypeList", ctypeList);
		return "/cargo/contract/jExtCproductUpdate";
//		return "redirect:/cargo/extcproduct/update.action";
	}
	
	@RequestMapping("/cargo/extcproduct/update.action")
	public String update(ExtCproduct extCproduct, Model model){
		
		extCproductService.update(extCproduct);
		model.addAttribute("contractProductId",extCproduct.getContractProductId());
		return "redirect:/cargo/extcproduct/toupdate.action";
	}

	@RequestMapping("/cargo/extcproduct/deleteById.action")
	public String deleteById(String id,String contractProductId, Model model){
		extCproductService.deleteById(id);
		model.addAttribute("contractProductId", contractProductId);		//传递主表ID
		
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
}
