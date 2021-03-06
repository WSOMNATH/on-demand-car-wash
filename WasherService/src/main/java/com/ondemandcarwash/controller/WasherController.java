	package com.ondemandcarwash.controller;
	
	import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.model.Ratings;
import com.ondemandcarwash.model.Washer;
import com.ondemandcarwash.repository.WasherRepository;
import com.ondemandcarwash.service.WasherService;
	
	@RestController
	@RequestMapping("/washer")
	public class WasherController {
		
	@Autowired
	private WasherService washerService;
	
	
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	
	//Creating/Adding Washer
	@PostMapping("/addwasher")
	public Washer saveWasher(@RequestBody Washer washer) 
	{
		return washerService.addWasher(washer);
	}

	//Reading all washer
	@GetMapping("/allwasher")
	public List<Washer> findAllWashers(){
		return washerService.getWashers();
		
	}
	
	//Reading Washer by ID
	@GetMapping("/allwasher/{id}")
	public Optional<Washer> getWasherById(@PathVariable int id)
	{
		return washerRepository.findById(id);
				
		
}
	//Updating washer Data by Id
		@PutMapping("/update/{id}")
		public ResponseEntity<Object> updateWasher(@PathVariable int id, @RequestBody Washer washer)
		{
		{
				washerRepository.save(washer);
				return new ResponseEntity<Object>("Washer updated successfully with id " +id, HttpStatus.OK);
			}
			
			
		}
		
		// Deleting washer Data by Id 
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Object> deleteWasher (@PathVariable int id)
		{
			 {
				washerService.deleteById(id);
				return new ResponseEntity<Object>("Washer deleted with id"+id,HttpStatus.OK);
			}
			

		}
		
		//washer read all order
		@GetMapping("/allorders")
		public List<Order> getallorders(){
			String baseurl="http://localhost:8083/order/allorder";
			Order[] allorders=restTemplate.getForObject(baseurl, Order[].class);
			
			return Arrays.asList(allorders);
		}
		//reading order by washer through id
		@GetMapping("/getallorders/{id}") 
		public Order getOrderById (@PathVariable("id") int id) 
		{
		  return restTemplate.getForObject("http://localhost:8083/order/order/" +id , Order.class);
		  
		  }
		
		
		//washer read all rating given by customer
		@GetMapping("/allratings")
		public List<Ratings> getallratings(){
			String baseurl="http://localhost:8081/admin/allratings";
			Ratings[] allratings=restTemplate.getForObject(baseurl, Ratings[].class);
			
			return Arrays.asList(allratings);
		}
}
	


