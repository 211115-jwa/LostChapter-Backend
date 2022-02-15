package com.revature.lostchapterbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.CreditCardInfo;
import com.revature.lostchapterbackend.model.Order;
import com.revature.lostchapterbackend.service.CreditCardInfoServ;


@RestController
@RequestMapping(path="/ccinfo")
@CrossOrigin("*")
public class CreditCardInfoController {
	//This controller is used for the following
		//getCCByUser GET /user/{userId}
		//updateCreditCardInfo PUT /update
		//addCreditCardInfo POST /add
		//getCCById GET /{ccId}
		//deletePurchase DELETE /{ccId}

	private static CreditCardInfoServ CreditCardInfoServ;
	public CreditCardInfoController() {
		super();
	}
	
	@Autowired
	public CreditCardInfoController(CreditCardInfoServ CreditCardInfoServ) {
		this.CreditCardInfoServ=CreditCardInfoServ;
	}
	@GetMapping(path = "user/{userId}") 
	public ResponseEntity<Object> getCCByUser(@PathVariable int userId){
		//gets the purchase by its PurchaseId
		
		List<CreditCardInfo> orders = CreditCardInfoServ.getCreditCardInfoByUser(userId);	
		if (orders != null)
			return ResponseEntity.ok(orders);
		else
			return ResponseEntity.notFound().build();
	}
	@PutMapping(path = "/update") 
	public ResponseEntity<Object> updateCreditCardInfo(@RequestBody CreditCardInfo newCreditCardInfo){
		//This methods responsibility it to add a new book to the CreditCardInfo collection if it doesnt exist already or to increase the quantity if it is present
		//This method uses the userID in order to find the users CreditCardInfo collection
		if (newCreditCardInfo !=null) {
			CreditCardInfoServ.upDateCreditCardInfo(newCreditCardInfo);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping(path = "/add") 
	public ResponseEntity<Object> addCreditCardInfo(@RequestBody CreditCardInfo ccAdd){
		
		if (ccAdd !=null) {
			CreditCardInfoServ.addCreditCardInfo(ccAdd);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	@GetMapping(path = "/{ccId}") 
	public ResponseEntity<Object> getCCById(@PathVariable int ccId) {
		//gets the purchase by its PurchaseId
		CreditCardInfo cc = CreditCardInfoServ.getCreditCardInfoById(ccId);
		if (cc != null)
			return ResponseEntity.ok(cc);
		else
			return ResponseEntity.notFound().build();
	}

	
	@DeleteMapping(path = "/{ccId}")
	public ResponseEntity<Void> deletePurchase(@RequestBody CreditCardInfo ccToDelete){
		//deletes the purchase by its PurchaseId
		if (ccToDelete !=null) {
			CreditCardInfoServ.deleteCreditCardInfo(ccToDelete);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
}
