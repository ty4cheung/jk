
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
	// /cargo/contract/toupdate.action
	@RequestMapping({"/cargo/export/list.action"})
	public String list(Model model){
		List<Export> dataList = exportService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jExportList";
	}
	
	// 购销合同查询列表
	@RequestMapping({"/cargo/export/contractList.action"})
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
	
	@RequestMapping("/cargo/export/update.action")
	public String update(Export export,
			String[] mr_id,
			Integer[] mr_orderNo,
			Integer[] mr_cnumber,
			Double[] mr_grossWeight,
			Double[] mr_netWeight,
			Double[] mr_sizeLength,
			Double[] mr_sizeWidth,
			Double[] mr_sizeHeight,
			Double[] mr_exPrice,
			Double[] mr_tax,
			Integer[] mr_changed){
		exportService.update(export,
				mr_id,
				mr_orderNo,
				mr_cnumber,
				mr_grossWeight,
				mr_netWeight,
				mr_sizeLength,
				mr_sizeWidth,
				mr_sizeHeight,
				mr_exPrice,
				mr_tax,
				mr_changed);
		
		return "redirect:/cargo/export/list.action";
		
	}
}
