package com.marketing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.dto.LeadData;
import com.marketing.entities.Lead;
import com.marketing.services.LeadService;
import com.marketing.util.EmailService;

@Controller
public class LeadController {
	@Autowired
	private LeadService leadService;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping("/create")
	public String createviewPage() {
		return "create_lead";
	}
	
	@RequestMapping("/saveLead")
	public String saveOneRecord(@ModelAttribute("l")Lead lead, ModelMap model) {
		leadService.saveLeadInfo(lead);
		emailService.sendEmail(lead.getEmail(), "i am good", "and how about you mother fuccker");
		model.addAttribute("msg", "Record saved successfuly");
		return "create_lead";
	}

//	@RequestMapping("/saveLead")
//	public String saveOneRecord(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email,@RequestParam("mobile") String mobile,ModelMap model) {
//		Lead lead = new Lead();
//		lead.setFirstName(firstName);
//		lead.setLastName(lastName);
//		lead.setEmail(email);
//		lead.setMobile(mobile);
//		
//		leadService.saveLeadInfo(lead);
//		model.addAttribute("msg", "Record saved successfuly");
//		return "create_lead";
//	}
//	@RequestMapping("/saveLead")
//	public String saveOneRecord(LeadData leadData, ModelMap model) {
//		Lead lead = new Lead();
//		lead.setFirstName(leadData.getFirstName());
//		lead.setLastName(leadData.getLastName());
//		lead.setEmail(leadData.getEmail());
//		lead.setMobile(leadData.getEmail());
//		leadService.saveLeadInfo(lead);
//		
//		model.addAttribute("msg", "Do not worry your secrets saved successfully");
//		return "create_lead";
//	}
//	//http://localhost:8080/listall
	@RequestMapping("/listall")
	public String listAllLead(Model model) {
		List<Lead> leads = leadService.getLead();
		model.addAttribute("leads", leads);
	return "list_leads";
	}
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id") long id,Model model) {
		leadService.deleteLead(id);
		
		List<Lead> leads = leadService.getLead();
		model.addAttribute("leads", leads);
		return "list_leads";
		
	}
	@RequestMapping("/update")
	public String getOneLead(@RequestParam("id")long id, Model model) {
		Lead lead =  leadService.getOneLead(id);
		model.addAttribute("lead", lead);
		return "update_lead";
	}
	@RequestMapping("/updateLead")
	public String updateLeadInfo(LeadData data, Model model) {
		Lead l = new Lead();
		l.setId(data.getId());
		l.setFirstName(data.getFirstName());
		l.setLastName(data.getLastName());
		l.setEmail(data.getEmail());
		l.setMobile(data.getMobile());
		
		leadService.saveLeadInfo(l);

		List<Lead> leads = leadService.getLead();
		model.addAttribute("leads", leads);
		return "list_leads";
		
	}
}
