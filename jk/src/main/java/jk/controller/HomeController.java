package jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController {

	@RequestMapping({"/home"})
	public String login(){
		 return "/index";
	}
	
	 @RequestMapping({"/fmain.action"})
	  public String fmain() {
	    return "/home/fmain";
	  }

	  @RequestMapping({"/title.action"})
	  public String title() {
	    return "/home/title";
	  }

	  @RequestMapping({"/left.action"})
	  public String left() {
	    return "/home/left";
	  }

	  @RequestMapping({"/main.action"})
	  public String main() {
	    return "/home/olmsgList";
	  }

	  @RequestMapping({"/sysadminMain.action"})
	  public String sysadminMain()
	  {
	    return "/sysadmin/main";
	  }

	  @RequestMapping({"/sysadminLeft.action"})
	  public String sysadminLeft() {
	    return "/sysadmin/left";
	  }

	  @RequestMapping({"/baseinfoMain.action"})
	  public String baseinfoMain()
	  {
	    return "/baseinfo/main";
	  }

	  @RequestMapping({"/baseinfoLeft.action"})
	  public String baseinfoLeft() {
	    return "/baseinfo/left";
	  }

	  @RequestMapping({"/cargoMain.action"})
	  public String cargoMain()
	  {
	    return "/cargo/main";
	  }

	  @RequestMapping({"/cargoLeft.action"})
	  public String cargoLeft() {
	    return "/cargo/left";
	  }
}
