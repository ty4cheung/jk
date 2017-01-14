package jk.controller.cargo.contract;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jk.controller.BaseController;
import jk.domain.Contract;
import jk.service.ContractService;

@Controller
public class ContractController extends BaseController {

	@Resource
	ContractService contractService;

	@RequestMapping("/cargo/contract/list.action")
	public String list(Model model) {
		 List dataList = this.contractService.find(null);
		  model.addAttribute("dataList", dataList);
		return "/cargo/contract/jContractList";
	}

	@RequestMapping(value="/cargo/contract/tocreate.action")
	public String tocreate(@RequestParam String contractId,Model model) {
		model.addAttribute("contractId",contractId);
		return "/cargo/contract/jContractCreate";
	}

	@RequestMapping({ "/cargo/contract/insert.action" })
	public String insert(Contract contract) {
		System.out.println("ContractController inser >>>>>>>>>>>>>>>>");
		this.contractService.insert(contract);

		return "redirect:/cargo/contract/list.action";
	}

	@RequestMapping({ "/cargo/contract/toupdate.action" })
	public String toupdate(String id, Model model) {
		Contract obj = this.contractService.get(id);
		model.addAttribute("obj", obj);

		return "/cargo/contract/jContractUpdate";
	}

	@RequestMapping({ "/cargo/contract/update.action" })
	public String update(Contract contract) {
		this.contractService.update(contract);

		return "redirect:/cargo/contract/list.action";
	}

	@RequestMapping({ "/cargo/contract/delete.action" })
	public String delete(String[] id) {
		this.contractService.delete(id);

		return "redirect:/cargo/contract/list.action";
	}

	@RequestMapping({ "/cargo/contract/toview.action" })
	public String toview(String id, Model model) {
		Contract obj = this.contractService.get(id);
		model.addAttribute("obj", obj);

		return "/cargo/contract/jContractView";
	}

	@RequestMapping({ "/cargo/contract/submit.action" })
	public String submit(String[] id) {
		this.contractService.submit(id);

		return "redirect:/cargo/contract/list.action";
	}

	@RequestMapping({ "/cargo/contract/cancel.action" })
	public String cancel(String[] id) {
		this.contractService.cancel(id);

		return "redirect:/cargo/contract/list.action";
	}
	
	@RequestMapping("/cargo/contract/deleteById.action")
	public String deleteById(String id,Model model){
		System.out.println("deleteById");
		this.contractService.deleteById(id);
		return "redirect:/cargo/contract/list.action";
	}
	
}
