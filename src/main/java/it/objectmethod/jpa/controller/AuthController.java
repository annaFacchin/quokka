package it.objectmethod.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.jpa.controller.beans.UserLog;
import it.objectmethod.jpa.model.User;
import it.objectmethod.jpa.repository.UserRepo;
import it.objectmethod.jpa.service.JwtAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtAuthService jwtUtil;

	@Autowired
	private UserLog userLog;

	@Autowired
	private UserRepo repo;

//	@RequestMapping("/login")
//	public ResponseEntity<Long> login(@RequestParam("username") String username,
//			@RequestParam("password") String password) {
//		System.out.println("executing login");
//		ResponseEntity<Long> resp = null;
//		User user = repo.findByUsernameAndPassword(username, password);
//
//		if (user != null) {
//			Random random = new Random();
//			Long token = random.nextLong();
//			log.getUserMap().put(token, user);
//			resp = new ResponseEntity<Long>(token, HttpStatus.OK);
//		} else {
//			resp = new ResponseEntity<Long>(HttpStatus.FORBIDDEN);
//		}
//		return resp;
//	}
//	

//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestParam("username") String username,
//			@RequestParam("password") String password) {
//		System.out.println("executing login");
//		ResponseEntity<String> resp = null;
//		String token = jwtUtil.authenticate(username, password);
//		if (token != null) {
//			resp = new ResponseEntity<String>(token, HttpStatus.OK);
//		} else {
//			resp = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
//		}
//		return resp;
//	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println("executing login");
		ResponseEntity<String> resp = null;
		User user = repo.findByUsernameAndPassword(username, password);

		if (user != null) {
			String token = jwtUtil.authenticate(username, password);
			userLog.getUserMap().put(token, user);
			resp = new ResponseEntity<String>(token, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		return resp;
	}

}
