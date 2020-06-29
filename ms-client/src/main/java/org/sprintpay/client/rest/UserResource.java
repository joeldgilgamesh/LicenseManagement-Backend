package org.sprintpay.client.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sprintpay.client.entities.User;
import org.sprintpay.client.repository.UserRepository;

@RestController
@RequestMapping("/api/")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;

	
	@PostMapping(value = "users")
    public User createUser(@Validated @RequestBody User user) {
        return userRepository.save(user);
    }
	
	
	@PutMapping(value = "users/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable long id,
                                                   @Validated @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        user.setId(userDetails.getId());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setRole(userDetails.getRole());

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
	
	@GetMapping("users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) throws ResourceNotFoundException {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
            return ResponseEntity.ok().body(user);
    }
	
	@DeleteMapping(value = "users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable long id)
    throws ResourceNotFoundException{
		User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

		userRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
	

}
