package com.cts.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cts.project.bean.VisitorBean;
import com.cts.project.bean.VisitorLoginBean;
import com.cts.project.entity.VisitorEntity;
import com.cts.project.service.VisitorServiceInterface;

@Controller
@RequestMapping(value = "/visitor")
public class VisitorController {

	@Autowired
	private VisitorServiceInterface service;
	
	 @RequestMapping(value="/logIn.html", method = RequestMethod.GET)
	    public String setupForm(Model model)
	    {
	         VisitorLoginBean vLogin = new VisitorLoginBean();
	         System.out.println("suspected erroe from login bean");
	         model.addAttribute("vLogin", vLogin);
	         return "index";
	    }

	 @RequestMapping(value="/searchVisitor.html", method = RequestMethod.POST)
	    public String submitForm(@ModelAttribute("vLogin") VisitorLoginBean vLogin,
	                            BindingResult result, SessionStatus status, Model theModel)
	    {
	        //Validation code start
	        boolean error = false;
	         
	        System.out.println(vLogin); //Verifying if information is same as input by user
	        if(vLogin.getUserName().isEmpty()==true || vLogin.getPassWord().isEmpty()==true) {
	        if(vLogin.getUserName().isEmpty()){
	            result.rejectValue("userName", "error.userName");
	            error = true;
	        }
	        if(vLogin.getPassWord().isEmpty()){
	            result.rejectValue("passWord", "error.passWord");
	            error = true;
	        }
	        }
	        if((vLogin.getUserName().isEmpty())==false && (vLogin.getPassWord().isEmpty())==false) {
		        String out = service.validateVisitorLogin(vLogin.getUserName(), vLogin.getPassWord());

	        
	        if(out.equals("notFound"))
	        {
	        	result.rejectValue("userName", "error.noUserFound");
	        	error = true;
	        }
	        if(out.equals("incorrect"))
	        {
	        	result.rejectValue("passWord", "error.wrongPassWord");
	        	error = true;
	        }
	        }
	        if(error) {
	        return "index";}
	        
	        theModel.addAttribute("visitor", service.getVisitorObject(vLogin.getUserName()));
	        status.setComplete();
	        return "visitormain";
	        }
	@RequestMapping(value = "/registration.html")
	public ModelAndView registerVisitor() {
		System.out.println("register visitor");
		return new ModelAndView("registration", "registrationAttribute", new VisitorBean());
	}

	@RequestMapping(value = "/saveVisitor.html", method = RequestMethod.POST)
	public ModelAndView getValues(@ModelAttribute("registrationAttribute") VisitorBean visitorBean, Model theModel) {
		System.out.println("save details");
		theModel.addAttribute("vLogin", new VisitorLoginBean());
		VisitorEntity vEntity = service.getVisitorObject(visitorBean.getUserName());
		if(vEntity!=null)
		{
			theModel.addAttribute("isRegistered", "username already exists, login");
			return new ModelAndView("index");
		}
		else {
		service.saveVisitor(visitorBean);
		
		return new ModelAndView("index");
		}
	}
	
	@RequestMapping(value="/updateDetail.html")
	public String updateVisistorDetail(@RequestParam("vName")String userName,Model theModel)
	{
		System.out.println("Inside UpdateVisitor Detail");
		VisitorEntity vEntity = service.getVisitorObject(userName);
		theModel.addAttribute("registrationAttribute", vEntity);
		
		return "updateRegistration";
	}
	@RequestMapping(value = "/updateVisitor.html", method = RequestMethod.POST)
	public String updateValues(@ModelAttribute("registrationAttribute") VisitorBean visitorBean, Model theModel) {
		theModel.addAttribute("visitor", service.updateVisitorObject(visitorBean));
		
		System.out.println(visitorBean);
		theModel.addAttribute("vLogin", new VisitorLoginBean());
		return "visitormain";
	}
	
	@RequestMapping(value="/changePWD.html")
	public ModelAndView changePWD(@RequestParam("uName")String userName,Model theModel)
	{
		theModel.addAttribute("status", userName);
		return new ModelAndView("changePWD");
	}
	@RequestMapping(value="/updatePWD.html", method = RequestMethod.POST)
	public ModelAndView updatePwd(@RequestParam("status")String userName, @RequestParam("password")String pwd, Model theModel)
	{
		boolean out =service.changePassword(userName, pwd);
		if(out==true)
		{
			theModel.addAttribute("messagePwd", "Password Changed SuccessFully");
		}
		else {
			theModel.addAttribute("messagePwd", "something Happened, unable to change password");

		}
		theModel.addAttribute("visitor", service.getVisitorObject(userName));
		return new ModelAndView("visitormain") ;
		
	}
}
