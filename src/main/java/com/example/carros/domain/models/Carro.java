package com.example.carros.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*@Columm(name = "nome")  caso o atributo não possua o mesmo nome da coluna  */
	private String nome;
	
	private String tipo;

	public Carro(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
}
