package br.ufc.quixada.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produto {
	private Integer id;
	private String codigo;
	private Float preco;
	private Integer qtdEstoque;
	private String dt_ult_entrada;	
}
