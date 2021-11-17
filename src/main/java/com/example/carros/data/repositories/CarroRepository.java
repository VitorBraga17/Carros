package com.example.carros.data.repositories;
import java.util.List;

import com.example.carros.domain.models.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro,Long> {

	List<Carro>  findByTipo(String tipo);
}
