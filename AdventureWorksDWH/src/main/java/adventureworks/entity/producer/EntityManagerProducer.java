package adventureworks.entity.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import adventureworks.interceptor.qualifiers.DwhSource;
import adventureworks.interceptor.qualifiers.DwhTarget;

@Named
@ApplicationScoped
public class EntityManagerProducer {


	
@PersistenceContext(unitName="dwhSource")
private EntityManager entityManagerSource;


@PersistenceContext(unitName="dwhTarget")
private EntityManager entityManagerTarget;

@Produces
@DwhSource
@ApplicationScoped
//@RequestScoped
public EntityManager getEntityManagerSource() {
    return entityManagerSource;
}
	

@Produces
@DwhTarget
@ApplicationScoped
//@RequestScoped
public EntityManager getEntityManagerTarget() {
    return entityManagerTarget;
}
	
}
