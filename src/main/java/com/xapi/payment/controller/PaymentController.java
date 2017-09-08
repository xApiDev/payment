package com.xapi.payment.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xapi.payment.service.PaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	public static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired PaymentService paymentService;
	/**
	 * 
		/payment/calculation	POST	- calculatePaymentAndPayeeAmounts		http://localhost:8080/ipay/payment/calculation
		/payment				POST	- placePayment							http://localhost:8080/ipay/payment
		/payment{user_id}		GET		- getUserPayments						http://localhost:8080/ipay/payment/100

	 */	
	
//	@CrossOrigin
	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserPayments(@PathVariable("user_id") Integer userId, HttpServletRequest request){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = "Metod getUserPayments( Integer userId) NOT IMPLEMENTED YET" + 
				"\nGet ALL User's placed PAYMENTS by user Id" + "\n Parameters, user Id = " + userId
				+ "\n" + "method = " + request.getMethod() +  ", URI - " + request.getRequestURI() + ", URL - " + request.getRequestURL() + ", -->" + RequestMethod.GET.toString();
		
		logger.info(info);
		
		Collection<?> placedPayments = paymentService.getAll( userId );
		return new ResponseEntity<String>(info + "\n" + placedPayments.toString(), HttpStatus.I_AM_A_TEAPOT);
	}	
	
//	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST) //value = "", 
	public ResponseEntity<?> placePayment(@RequestBody Object payment){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = "Metod placePayment( Integer userId) NOT IMPLEMENTED YET" + 
				"\nPlace to execute User's placed PAYMENT by payment object" + "\n Parameters, payment = " + payment.toString();
		
		logger.info(info);
		
		Object paymentReference = paymentService.placePayment(payment);
		return new ResponseEntity<Object>(info + "\n" + paymentReference.toString(), HttpStatus.I_AM_A_TEAPOT);
	}
	
//	@CrossOrigin
	@RequestMapping(value = "/calculation", method = RequestMethod.POST)
	public ResponseEntity<?> calculatePaymentAndPayeeAmounts(@RequestBody Object paymentPayeeAmounts){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = "Metod calculatePaymentAndPayeeAmounts( Integer userId) NOT IMPLEMENTED YET" + 
				"\nPlace to calculate User's to be placed PAYMENT by payment details" + "\n Parameters, payment = " + paymentPayeeAmounts.toString();
		
		logger.info(info);
		
		Object calculatedResult = paymentService.calculate(paymentPayeeAmounts);
		return new ResponseEntity<Object>(info + "\n" + calculatedResult.toString(), HttpStatus.I_AM_A_TEAPOT);
	}
}
