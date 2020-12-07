package br.com.talentfour.recrutamento.Desafio.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.talentfour.recrutamento.Desafio.model.Cidade;
import br.com.talentfour.recrutamento.Desafio.model.Estado;
import br.com.talentfour.recrutamento.Desafio.service.ControleEstadoCidadeBean;

@ManagedBean
@ViewScoped
public class CadastroMB {
	
	@EJB
	private ControleEstadoCidadeBean cadastroCidadeBean;
	
	private List<Estado> estados;

	private String estado = "Santa Catarina";
	private Estado estadoSelecionado;
	private Cidade cidadeSelecionada;
	private String imagemEstado = "santa_catarina.png";
	
	
	@PostConstruct
	public void init() {
		estados = cadastroCidadeBean.getEstado();
		atualizaPagina();
	}
	
	public void atualizaPagina() {
		Optional<Estado> oEstado = estados.stream().filter(estadoTemp -> estadoTemp.getNome().equalsIgnoreCase(estado)).findAny();
		if (oEstado.isPresent()) {
			estadoSelecionado = oEstado.get();
		}
		imagemEstado = estadoSelecionado.getImage();
		cidadeSelecionada = new Cidade();
		RequestContext.getCurrentInstance().update("mainContent");
	}
	
	public void atualizarEstado() {
		Optional<Estado> oEstado = estados.stream().filter(estadoTemp -> estadoTemp.getNome().equalsIgnoreCase(estado)).findAny();
		if (oEstado.isPresent()) {
			estadoSelecionado = oEstado.get();
		}
		imagemEstado = estadoSelecionado.getImage();
		RequestContext.getCurrentInstance().update("mainContent");
	}
	
	public void salvarCidade() {
		
		FacesMessage m = null;
		
		if (cadastroCidadeBean.adiconarCidade(estadoSelecionado, cidadeSelecionada)) {
			cidadeSelecionada = new Cidade();
			m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salva!", "Cidade Salva com sucesso");
		}else {
			m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha!", "Cidade já existe nesse estado ");
		}
		FacesContext.getCurrentInstance().addMessage(null, m);
	}
	
	public List<Estado> getEstados(){
		return cadastroCidadeBean.getEstado();
	}
	
	public Boolean estadoSelecionadoIsPresent() {
		return estadoSelecionado != null ? true : false;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getImagemEstado() {
		return imagemEstado;
	}

	public void setImagemEstado(String imagemEstado) {
		this.imagemEstado = imagemEstado;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	

}
