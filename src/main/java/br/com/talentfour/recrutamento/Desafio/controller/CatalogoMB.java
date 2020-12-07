package br.com.talentfour.recrutamento.Desafio.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import br.com.talentfour.recrutamento.Desafio.model.Estado;
import br.com.talentfour.recrutamento.Desafio.service.ControleEstadoCidadeBean;

@Model
public class CatalogoMB {

	private List<Estado> estados;
	
	@EJB
	private ControleEstadoCidadeBean controleEstadoCidadeBean;
	
	@PostConstruct
	public void init() {
		setEstados(controleEstadoCidadeBean.getEstado());
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	
}
