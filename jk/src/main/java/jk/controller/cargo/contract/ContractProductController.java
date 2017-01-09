package jk.controller.cargo.contract;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jk.controller.BaseController;
import jk.domain.ContractProduct;
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

	    return "/cargo/contract/jContractProductCreate.jsp";
	  }

	  @RequestMapping({"/cargo/contractproduct/insert.action"})
	  public String insert(ContractProduct contractProduct, Model model)
	  {
	    this.contractProductService.insert(contractProduct);

	    model.addAttribute("contractId", contractProduct.getContractId());

	    return "redirect:/cargo/contractproduct/tocreate.action";
	  }
}
