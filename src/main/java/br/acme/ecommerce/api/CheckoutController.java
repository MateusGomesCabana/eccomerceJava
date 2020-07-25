package br.acme.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.acme.ecommerce.model.Sale;
import br.acme.ecommerce.service.CheckoutServices;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	private CheckoutServices services;

	@PostMapping("/sale")
	public ResponseEntity<Boolean> sale(@RequestBody Sale sale) {
		services.saleProduct(sale);
		return ResponseEntity.ok().build();
	}

}