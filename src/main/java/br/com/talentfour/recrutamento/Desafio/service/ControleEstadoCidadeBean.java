package br.com.talentfour.recrutamento.Desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.talentfour.recrutamento.Desafio.data.CadastroRepository;
import br.com.talentfour.recrutamento.Desafio.model.Cidade;
import br.com.talentfour.recrutamento.Desafio.model.Estado;

@Stateless
public class ControleEstadoCidadeBean {

	@Inject
	private EntityManager em;
	
	@Inject
    private Logger log;

	@EJB
	CadastroRepository cadastroRepository;
	
	@PostConstruct
	public void init() {
//		Estado estado = new Estado();
//		estado.setImage("santa_catarina.png");
//		estado.setNome("Santa Catarina");
//		em.persist(estado);
//		em.flush();
	}
	
	public List<Estado> getEstado(){
		 CriteriaBuilder builder = em.getCriteriaBuilder();
		 CriteriaQuery<Estado> criteria = builder.createQuery(Estado.class);
		 Root<Estado> root = criteria.from(Estado.class);
		 List<Estado> estados = em.createQuery(criteria).getResultList();
		 return estados;
	}
	
	public boolean adiconarCidade(Estado estado, Cidade cidade) {
		if (estado.getCidades() == null || estado.getCidades().size() == 0) {
			estado.setCidades(new ArrayList<Cidade>());
		}
		
		Optional<Cidade> opCidade = estado.getCidades().stream().filter(cidadeTemp -> cidade.getNome().equals(cidadeTemp.getNome())).findAny();
		if (opCidade.isPresent()) {
			return false;
		}else {
			cidade.setEstado(estado);
			estado.getCidades().add(cidade);
			em.persist(cidade);
			return true;
		}
	}
}
