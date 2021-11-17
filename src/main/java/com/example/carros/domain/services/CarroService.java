package com.example.carros.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.carros.domain.models.Carro;
import com.example.carros.data.repositories.CarroRepository;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;

import com.example.carros.domain.models.dto.CarroDTO;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository rep;
	
	public List<CarroDTO> getCarros() {
		new CarroDTO();
		return rep.findAll().stream().map(c -> CarroDTO.create(c) )// pode ser map(CarroDTO::new) , mapeia todos os carros salvos no repositório para CarroDTO
		 .collect(Collectors.toList());	 
		 
	}

	public Optional<CarroDTO> getCarroById(Long id) {
		return rep.findById(id).map(c -> CarroDTO.create(c));

		/*
		 * Optional<Carro> carro = rep.findById(id); return carro.map(c ->
		 * Optional.of(new CarroDTO(c))).orElse(null);
		 */
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo).stream()
				.map(c -> CarroDTO.create(c))
				.collect(Collectors.toList());
	}
	
	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não Foi possível inserir o registro!");

		return CarroDTO.create(rep.save(carro));
	}
	
	
	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id,"Não foi possível atualizar o registro");

		// busca		
		Optional<Carro> optional = rep.findById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			// copiar as prop
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());
			
			// Atualiza o carro
			rep.save(db);
			
			return CarroDTO.create(db);
		}else {
			return null;
			//throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}
	
	public boolean delete(Long id) {
		if(getCarroById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
	}
}

/* carros.add(new Carro(1L,"Fusca"));
		carros.add(new Carro(2L,"Brasilia"));
		carros.add(new Carro(3L,"Chevete")); */