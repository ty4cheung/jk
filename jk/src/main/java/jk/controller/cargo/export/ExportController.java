
package jk.controller.cargo.export;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jk.controller.BaseController;
import jk.domain.Contract;
import jk.domain.Export;
import jk.service.ExportService;

/**
 * @Description: TODO
 * @Author:	tyler
 * @Company:	
 * @CreateDate:	2017年2月13日
 */
@Controller
public class ExportController extends BaseController {

	@Resource
	ExportService exportService;
	
	@RequestMapping("/cargo/export/list.action")
	public String list(Model model){
		List<Export> dataList = exportService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jExprotList";
	}
	
	// 购销合同查询列表
	@RequestMapping("/cargp/export/contractList.action")
	public String contractList(Model model){
		List<Contract> dataList = exportService.getContract();
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jContratctList";
	}
	
	// 报运新增，直接后台保存
	@RequestMapping("/cargo/export/insert.action")
	public String insert(@RequestParam("id")String[] contractIds ){
		// 
		exportService.insert(contractIds);
		return "redirect:/cargo/export/list.action";
	}
}
