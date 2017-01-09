package jk.controller.cargo.contract;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping({ "/cargo/contract/tocreate.action" })
	public String tocreate() {
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
}
