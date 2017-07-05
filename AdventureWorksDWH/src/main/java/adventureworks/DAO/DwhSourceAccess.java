package adventureworks.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import adventureworks.entitySource.Salesorderheader;
import adventureworks.interceptor.Transactional;
import adventureworks.interceptor.qualifiers.DwhSource;

@Named
@ApplicationScoped
public class DwhSourceAccess implements Serializable{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Inject
Logger log;
	
@Inject
@DwhSource
	EntityManager entityManager;




private List<Salesorderheader> getAllModelsIterable(int offset, int max)
{
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Salesorderheader> cq = cb.createQuery(Salesorderheader.class);
	Root<Salesorderheader> from = cq.from(Salesorderheader.class);
	cq.select(from).orderBy(cb.asc(from.get("modifiedDate")));
	TypedQuery<Salesorderheader> q = entityManager.createQuery(cq);
	List<Salesorderheader> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
	return item;
	
    }

@Transactional
private void iterateAll()
{
    int offset = 0;

    List<Salesorderheader> salesHeaders;
    while ((salesHeaders = this.getAllModelsIterable(offset, 100)).size() > 0)
    {
        entityManager.getTransaction().begin();
        for (Salesorderheader salesHeader : salesHeaders)
        {
            log.info("do something with model: " + salesHeader.toString());
        }
        offset += salesHeaders.size();
    }
}

}
