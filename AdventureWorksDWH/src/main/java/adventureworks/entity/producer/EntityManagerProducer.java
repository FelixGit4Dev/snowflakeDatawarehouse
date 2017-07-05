package adventureworks.entity.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import adventureworks.interceptor.qualifiers.DwhSource;
import adventureworks.interceptor.qualifiers.DwhTarget;

public class EntityManagerProducer {

	
@PersistenceContext(unitName="dwhSource")
private EntityManager entityManagerSource;


@PersistenceContext(unitName="dwhTarget")
private EntityManager entityManagerTarget;

@Produces
@DwhSource
@RequestScoped
public EntityManager getEntityManagerSource() {
    return entityManagerSource;
}
	

@Produces
@DwhTarget
@RequestScoped
public EntityManager getEntityManagerTarget() {
    return entityManagerTarget;
}
	
}
