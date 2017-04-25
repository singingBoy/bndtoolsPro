package wds.commons.apis;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public abstract class JpaBasePersistenceService implements BasePersistenceService {

    protected abstract EntityManager getEntityManager();

    @Override
    public <T extends Serializable> void remove(Class<T> clazz, String id) {
        T object = find(clazz, id);
        getEntityManager().remove(object);
    }

    @Override
    public <T extends Serializable> T find(Class<T> clazz, String id) {
        T object = getEntityManager().find(clazz, id);
        return object;
    }

    @Override
    public <T extends Serializable> T persist(T object) {
        getEntityManager().persist(object);
        getEntityManager().flush();
        return object;
    }

    @Override
    public <T extends Serializable> T merge(T object) {
        getEntityManager().merge(object);
        return object;
    }

    @Override
    public int count(Class<? extends Serializable> clazz, QueryParams params) {
        CriteriaQuery<?> criteriaQuery = createCriteriaQuery(clazz, params);
        TypedQuery<?> typedQuery = getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList().size();
    }

    @Override
    public <T extends Serializable> List<T> query(Class<T> clazz, QueryParams params) {
        CriteriaQuery<T> criteriaQuery = createCriteriaQuery(clazz, params);
        TypedQuery<T> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(params.getMax());
        typedQuery.setFirstResult(params.getFirst());
        return typedQuery.getResultList();
    }

    private <T> CriteriaQuery<T> createCriteriaQuery(Class<T> clazz, QueryParams params) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        List<Predicate> predicates = new ArrayList<>();
        for ( Query query : params.getQuerys() ) {
            predicates.add(cb.and(parsePredicate(cb, root, query)));
        }
        String sorts = params.getSort();
        if ( sorts != null ) {
            for ( String sort : sorts.split(",") ) {
                switch ( sort.substring(0, 1) ) {
                    case "2":
                        criteriaQuery.orderBy(cb.asc(root.get(sort.substring(1))));
                        break;
                    case "8":
                        criteriaQuery.orderBy(cb.desc(root.get(sort.substring(1))));
                        break;
                }
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return criteriaQuery;
    }

    private Predicate parsePredicate(CriteriaBuilder cb, Root<?> root, Query query) {
        switch ( query.getCondition() ) {
            case LIKE:
                return cb.like(root.get(query.getField()), "%" + query.getValue() + "%");
            case EQUAL:
                return cb.equal(root.get(query.getField()), query.getValue());
            case BETWEEN:
                return cb.between(root.get(query.getField()), query.getValue(), query.getValue2());
        }
        return null;
    }

}
