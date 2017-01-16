package jk.controller.cargo.contract;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jk.controller.BaseController;
import jk.domain.ContractProduct;
import jk.domain.Factory;
import jk.service.ContractProductService;
import jk.service.FactoryService;

/**
 * @author zhangtai
 *
 */
@Controller
public class ContractProductController extends BaseController {
	@Resource
	  ContractProductService contractProductService;

	  @Resource
	  FactoryService factoryService;

	  @RequestMapping({"/cargo/contractproduct/tocreate.action"})
	  public String tocreate(String contractId, Model model)
	  {
	    model.addAttribute("contractId", contractId);

	    List factoryList = this.factoryService.getFactoryList();
	    model.addAttribute("factoryList", factoryList);

	    Map paraMap = new HashMap();
	    paraMap.put("contractId", contractId);

	    List dataList = this.contractProductService.find(paraMap);
	    model.addAttribute("dataList", dataList);

	    return "/cargo/contract/jContractProductCreate";
	  }

	  @RequestMapping({"/cargo/contractproduct/insert.action"})
	  public String insert(ContractProduct contractProduct, Model model)
	  {
	    this.contractProductService.insert(contractProduct);

	    model.addAttribute("contractId", contractProduct.getContractId());

	    return "redirect:/cargo/contractproduct/tocreate.action";
	  }
	  
	  @RequestMapping({"/cargo/contractproduct/toupdate.action"})
	  public String toupdate(String id, Model model){
		  ContractProduct obj = contractProductService.get(id);
			model.addAttribute("obj", obj);
			//准备生产厂家的下拉列表
			List<Factory> factoryList = factoryService.getFactoryList();
			model.addAttribute("factoryList", factoryList);
			return "/cargo/contract/jContractProductUpdate";
	  }
	  
	//修改保存
		@RequestMapping("/cargo/contractproduct/update.action")
		public String update(ContractProduct contractProduct ,Model model){
			contractProductService.update(contractProduct);
			model.addAttribute("contractId", contractProduct.getContractId());
			return "redirect:/cargo/contractproduct/tocreate.action";
		}
		
		//删除
		@RequestMapping("/cargo/contractproduct/deleteById.action")
		public String deleteById(String id, String contractId, Model model){
			contractProductService.deleteById(id);
			model.addAttribute("contractId", contractId);			//传递主表ID
			
			return "redirect:/cargo/contractproduct/tocreate.action";
		}
	  
	  
	  
	 
}
