package com.springapp.mvc.controller;

import com.springapp.mvc.model.Customers;
import com.springapp.mvc.model.Position;
import com.springapp.mvc.repository.QueryAnalogue;
import com.springapp.mvc.repository.XMLConverter;
import com.springapp.mvc.validation.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class HelloController {

	private static final String XML_FILE_NAME = "customers_1.xml";

	private QueryAnalogue queryAnalogue;

	private FormValidator formValidator;

	private XMLConverter converter;

	@Autowired
	public HelloController (XMLConverter converter, QueryAnalogue queryAnalogue, FormValidator formValidator) {
		this.converter = converter;
		this.queryAnalogue = queryAnalogue;
		this.formValidator = formValidator;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printXML(Model model) throws IOException {

		ClassLoader classLoader = HelloController.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(XML_FILE_NAME);

		Customers customers = (Customers)converter.convertFromXMLToObject(inputStream);
		List list = customers.getCustomer();
		model.addAttribute("customers", list);
		model.addAttribute("sumAll", queryAnalogue.sumAll(customers));
		model.addAttribute("maxOrdersSum", queryAnalogue.getClientWithMaxOrdersPrice(customers));
		model.addAttribute("maxOrderPrice", queryAnalogue.getMaxOrderPrice(customers));
		model.addAttribute("minOrderPrice", queryAnalogue.getMinOrderPrice(customers));
		model.addAttribute("ordersCount", queryAnalogue.getOrdersCount(customers));
		model.addAttribute("avgPrice", queryAnalogue.avgOrdersPrice(queryAnalogue.sumAll(customers), queryAnalogue.getOrdersCount(customers)));
		model.addAttribute("position", new Position());
		return "/pages/hello";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String printzXML(@ModelAttribute("position") Position position, BindingResult bindingResult, Model model) throws IOException {

		ClassLoader classLoader = HelloController.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(XML_FILE_NAME);

		this.formValidator.validate(position, bindingResult);
		Customers customers = (Customers)converter.convertFromXMLToObject(inputStream);
		List list = customers.getCustomer();
		model.addAttribute("customers", list);
		model.addAttribute("sumAll", queryAnalogue.sumAll(customers));
		model.addAttribute("maxOrdersSum", queryAnalogue.getClientWithMaxOrdersPrice(customers));
		model.addAttribute("maxOrderPrice", queryAnalogue.getMaxOrderPrice(customers));
		model.addAttribute("minOrderPrice", queryAnalogue.getMinOrderPrice(customers));
		model.addAttribute("ordersCount", queryAnalogue.getOrdersCount(customers));
		model.addAttribute("avgPrice", queryAnalogue.avgOrdersPrice(queryAnalogue.sumAll(customers), queryAnalogue.getOrdersCount(customers)));
		if (bindingResult.hasErrors()) {
			return "/pages/hello";
		}
		model.addAttribute("customersNames", queryAnalogue.getCustomersNames(customers, position.getPrice()));
		model.addAttribute("position", new Position());
		return "/pages/helloresult";
	}

}