package com.example.carros.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.carros.domain.models.Carro;
import com.example.carros.domain.services.CarroService;
import com.example.carros.domain.models.dto.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

	@Autowired//injeção de dependências
	private CarroService service;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity get() {
		return ResponseEntity.ok(service.getCarros());
		// return new ResponseEntity<>(,HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<CarroDTO> carro = service.getCarroById(id);

		return carro
				.map(c -> ResponseEntity.ok(c))// pode ser ResponseEntity::ok
				.orElse(ResponseEntity.notFound().build());
		/*
		 * return carro.isPresent() ? ResponseEntity.ok(carro.get()):
		 * ResponseEntity.notFound().build();
		 */
		/*
		 * if(carro.isPresent()) { return ResponseEntity.ok(carro.get()); }else { return
		 * ResponseEntity.notFound().build(); }
		 */
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getCarrosByTipo(tipo);

		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}


	@SuppressWarnings("rawtypes")
	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity post(@RequestBody Carro carro) {
		
		try{
			CarroDTO c = service.insert(carro);

			URI location = getUri(c.getId());
			return ResponseEntity.created(location).build();
		} catch(Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	private URI getUri(Long id){
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(id).toUri();
	}

	 @SuppressWarnings("rawtypes")
	 @PutMapping("/{id}") 
	 public ResponseEntity put(@PathVariable Long id, @RequestBody Carro carro) { 
		 
		 carro.setId(id);
		 
		 CarroDTO c = service.update(carro,id);
		 
	 
		 return c != null ?
				 ResponseEntity.ok(c):
				 ResponseEntity.notFound().build();
		 }
	 

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {

		return service.delete(id) ?
				ResponseEntity.ok().build() :
				ResponseEntity.notFound().build();
	}
}

/*
 * @GetMapping("/login") public String login(@RequestParam("login") String
 * login,@RequestParam("senha") String senha) { return "Login " + login +
 * ", senha: " + senha; }
 */

/*
 * @GetMapping("login/{login}/senha/{senha}") public String
 * login(@PathVariable("login") String login,@PathVariable("senha") String
 * senha) { return "" + "" + "Login " + login + ", senha: " + senha; }
 * 
 * @GetMapping("/carros/{id}") public String getCarroById(@PathVariable("id")
 * Long id) { return "Carro by id " + id; }
 * 
 * @GetMapping("/carros/tipo/{tipo}") public String
 * getCarroById(@PathVariable("tipo") String tipo) { return "Lista de Carros " +
 * tipo; }
 */

/*
 * @PostMapping("/login") public String login(@RequestParam("login") String
 * login,@RequestParam("senha") String senha) { return "Login " + login +
 * ", senha: " + senha; }
 */