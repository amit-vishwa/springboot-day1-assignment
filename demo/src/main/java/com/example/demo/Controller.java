package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.User;

@RestController
public class Controller {
	private static Map<String, User> UserRepo = new HashMap<>();
	   static {
	      User honey = new User();
	      honey.setId("1");
	      honey.setName("Honey");
	      UserRepo.put(honey.getId(), honey);
	      
	      User almond = new User();
	      almond.setId("2");
	      almond.setName("Almond");
	      UserRepo.put(almond.getId(), almond);
	   }
	   
//	   @RequestMapping(value = "/Users/{id}", method = RequestMethod.DELETE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	   @RequestMapping(value = "/Users/{id}", method = RequestMethod.DELETE)
	   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
	      UserRepo.remove(id);
	      return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
	   }
	   
	   @RequestMapping(value = "/Users/{id}", method = RequestMethod.PUT)
	   public ResponseEntity<Object> updateUser(@PathVariable("id") String id, @RequestBody User User) { 
	      UserRepo.remove(id);
	      User.setId(id);
	      UserRepo.put(id, User);
	      return new ResponseEntity<>("User is updated successfully", HttpStatus.OK);
	   }
	   
	   @RequestMapping(value = "/Users", method = RequestMethod.POST)
	   public ResponseEntity<Object> createUser(@RequestBody User User) {
	      UserRepo.put(User.getId(), User);
	      return new ResponseEntity<>("User is created successfully", HttpStatus.CREATED);
	   }
	   
	   @GetMapping(value = "/Users")
	   public ResponseEntity<Object> getUser() {
	      return new ResponseEntity<>(UserRepo.values(), HttpStatus.OK);
	   }
}
