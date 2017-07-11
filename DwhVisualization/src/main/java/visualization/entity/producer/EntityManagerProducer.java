package visualization.entity.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

	
@PersistenceContext(unitName="dwhTarget")
private EntityManager entityManager;


@Produces
@RequestScoped
public EntityManager getEntityManager() {
    return entityManager;
}
	
}
