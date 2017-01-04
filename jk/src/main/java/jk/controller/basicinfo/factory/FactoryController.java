package jk.controller.basicinfo.factory;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jk.controller.BaseController;
import jk.domain.Factory;
import jk.service.FactoryService;

public class FactoryController extends BaseController {
	
	@Resource
	FactoryService factoryService;
	
	 @RequestMapping({"/basicinfo/factory/list.action"})
	  public String list(Model model)
	  {
	    List dataList = this.factoryService.find(null);
	    model.addAttribute("dataList", dataList);

	    return "/basicinfo/factory/jFactoryList";
	  }

	  @RequestMapping({"/basicinfo/factory/tocreate.action"})
	  public String tocreate()
	  {
	    return "/basicinfo/factory/jFactoryCreate";
	  }

	  @RequestMapping({"/basicinfo/factory/insert.action"})
	  public String insert(Factory factory)
	  {
	    this.factoryService.insert(factory);

	    return "redirect:/basicinfo/factory/list.action";
	  }

	  @RequestMapping({"/basicinfo/factory/toupdate.action"})
	  public String toupdate(String id, Model model)
	  {
	    Factory obj = this.factoryService.get(id);
	    model.addAttribute("obj", obj);

	    return "/basicinfo/factory/jFactoryUpdate";
	  }

	  @RequestMapping({"/basicinfo/factory/update.action"})
	  public String update(Factory factory)
	  {
	    this.factoryService.update(factory);

	    return "redirect:/basicinfo/factory/list.action";
	  }

	  @RequestMapping({"/basicinfo/factory/deleteById.action"})
	  public String deleteById(String id)
	  {
	    this.factoryService.deleteById(id);

	    return "redirect:/basicinfo/factory/list.action";
	  }

	  @RequestMapping({"/basicinfo/factory/delete.action"})
	  public String delete(@RequestParam("id") String[] ids)
	  {
	    this.factoryService.delete(ids);

	    return "redirect:/basicinfo/factory/list.action";
	  }

	  @RequestMapping({"/basicinfo/factory/toview.action"})
	  public String toview(String id, Model model)
	  {
	    Factory obj = this.factoryService.get(id);
	    model.addAttribute("obj", obj);

	    return "/basicinfo/factory/jFactoryView";
	  }

	  @RequestMapping({"/basicinfo/factory/start.action"})
	  public String start(@RequestParam("id") String[] ids)
	  {
	    this.factoryService.start(ids);

	    return "redirect:/basicinfo/factory/list.action";
	  }

	  @RequestMapping({"/basicinfo/factory/stop.action"})
	  public String stop(@RequestParam("id") String[] ids)
	  {
	    this.factoryService.stop(ids);

	    return "redirect:/basicinfo/factory/list.action";
	  }

}
