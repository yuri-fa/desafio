package br.com.talentfour.recrutamento.Desafio.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.talentfour.recrutamento.Desafio.model.Estado;

@Stateless
public class CadastroRepository {

	@Inject
	private EntityManager em;
	 
	 public List<Estado> findAll(){
		 CriteriaBuilder builder = em.getCriteriaBuilder();
		 CriteriaQuery<Estado> criteria = builder.createQuery(Estado.class);
		 Root<Estado> root = criteria.from(Estado.class);
		 return em.createQuery(criteria).getResultList();
	 }
	 
	 
	
}
