package org.sprintpay.product.rest;

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
import org.sprintpay.product.entities.Product;
import org.sprintpay.product.repository.ProductRepository;

@RestController
@RequestMapping("/api/")
public class ProductRessource {
	
	@Autowired
	private ProductRepository productRepository;

	
	@PostMapping(value = "products")
    public Product createproduct(@Validated @RequestBody Product product) {
        return productRepository.save(product);
    }
	
	
	@PutMapping(value = "products/{id}")
    public ResponseEntity<Product> updateEmployee(@PathVariable long id,
                                                   @Validated @RequestBody Product productDetails) throws ResourceNotFoundException {
		Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + id));

        product.setId(productDetails.getId());
        product.setName(productDetails.getName());
        product.setCode(productDetails.getCode());
        product.setVersion(productDetails.getVersion());

        final Product updatedproduct = productRepository.save(product);
        return ResponseEntity.ok(updatedproduct);
    }
	
	@GetMapping("products")
	public List<Product> getAllproducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
    public ResponseEntity<Product> getproduct(@PathVariable Long id) throws ResourceNotFoundException {
		Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
            return ResponseEntity.ok().body(product);
    }
	
	@DeleteMapping(value = "products/{id}")
    public Map<String, Boolean> deleteproduct(@PathVariable long id)
    throws ResourceNotFoundException{
		Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

		productRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
	

}
