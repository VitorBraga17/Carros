package com.example.carros.domain.models.dto;

import org.modelmapper.ModelMapper;

import com.example.carros.domain.models.Carro;

import lombok.Data;

@Data// lombok: faz a criação dos getters,setters, equals e hashcode da classe
public class CarroDTO {

	private Long id;
	private String nome;
	private String tipo;
	

	//o modelmapper mapeia de Carro -> CarroDTO
	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}
	
}
