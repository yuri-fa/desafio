package br.com.talentfour.recrutamento.Desafio.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbestado")
public class Estado implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="estid")
	private Long id;
	@Column(name="estnome")
	private String nome;
	@Column(name="estimage")
	private String image;
	@OneToMany(mappedBy = "estado",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Cidade> cidades;
	
	
	public Estado() {
		super();
	}

	public Estado(String nome,String image) {
		super();
		this.nome = nome;
		this.setImage(image);
	}
	
	public Integer getPopulacao() {
		return cidades.stream().map(Cidade :: getPopulacao).collect(Collectors.summingInt(Integer::intValue));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
}
