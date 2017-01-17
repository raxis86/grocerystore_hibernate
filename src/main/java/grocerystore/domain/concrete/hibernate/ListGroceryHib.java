package grocerystore.domain.concrete.hibernate;

import grocerystore.domain.abstracts.IRepositoryListGrocery;
import grocerystore.domain.entityes.ListGroceryEntity;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.ListGroceryException;
import grocerystore.domain.models.ListGrocery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 17.01.2017.
 */
@Repository
public class ListGroceryHib extends HibImplementation implements IRepositoryListGrocery{
    private static final Logger logger = LoggerFactory.getLogger(ListGroceryHib.class);

    private ListGrocery convert(ListGroceryEntity lge){
        ListGrocery listGrocery = new ListGrocery();
        listGrocery.setId(UUID.fromString(lge.getId()));
        listGrocery.setGroceryId(UUID.fromString(lge.getGroceryid()));
        listGrocery.setQuantity(lge.getQuantity());
        return listGrocery;
    }

    private ListGroceryEntity convert(ListGrocery lg){
        ListGroceryEntity groceriesEntity = new ListGroceryEntity();
        groceriesEntity.setId(lg.getId().toString());
        groceriesEntity.setGroceryid(lg.getGroceryId().toString());
        groceriesEntity.setQuantity(lg.getQuantity());
        return groceriesEntity;
    }

    @Override
    public List<ListGrocery> getAll() throws ListGroceryException {
        List<ListGroceryEntity> listGroceryEntities;
        List<ListGrocery> listGroceryList = new ArrayList<>();
        EntityManager entityManager = factory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ListGroceryEntity> q = criteriaBuilder.createQuery(ListGroceryEntity.class);
            Root<ListGroceryEntity> root = q.from(ListGroceryEntity.class);
            TypedQuery<ListGroceryEntity> query = entityManager.createQuery(q);

            listGroceryEntities =  query.getResultList();

            for (ListGroceryEntity ge:listGroceryEntities){
                listGroceryList.add(convert(ge));
            }
        }
        catch (Exception e){
            logger.error("Cant getall",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно получить записи из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return listGroceryList;
    }

    @Override
    public ListGrocery getOne(UUID id) throws ListGroceryException {
        ListGrocery listGrocery;
        EntityManager entityManager = factory.createEntityManager();

        try {
            ListGroceryEntity groceriesEntity = entityManager.find(ListGroceryEntity.class, id.toString());
            listGrocery = convert(groceriesEntity);
        }
        catch (Exception e){
            logger.error("Cant getOne ListGrocery!", e);
            throw new ListGroceryException("Проблема с базой данных: невозможно получить запись из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }


        return listGrocery;
    }

    @Override
    public boolean create(ListGrocery entity) throws ListGroceryException {
        ListGroceryEntity listGroceryEntity = convert(entity);
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(listGroceryEntity);

            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant create",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно создать запись в таблице связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }


        return true;
    }

    @Override
    public boolean delete(UUID id) throws ListGroceryException {
        EntityManager entityManager = factory.createEntityManager();

        ListGroceryEntity listGroceryEntity = entityManager.find(ListGroceryEntity.class, id.toString());

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(listGroceryEntity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant delete",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно удалить запись из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean update(ListGrocery entity) throws ListGroceryException {
        EntityManager entityManager = factory.createEntityManager();

        ListGroceryEntity listGroceryEntity = convert(entity);

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(listGroceryEntity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant update",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно изменить запись в таблице связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public List<ListGrocery> getListById(UUID id) throws ListGroceryException {
        List<ListGroceryEntity> listGroceryEntities;
        List<ListGrocery> listGroceryList = new ArrayList<>();
        EntityManager entityManager = factory.createEntityManager();

        try {

            //EntityType<ListGroceryEntity> Lge = entityManager.getMetamodel().entity(ListGroceryEntity.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ListGroceryEntity> q = criteriaBuilder.createQuery(ListGroceryEntity.class);
            Root<ListGroceryEntity> lge = q.from(ListGroceryEntity.class);

            q.select(lge).where(criteriaBuilder.equal(lge.get("id"),id.toString()));

            /*Predicate condition = criteriaBuilder.equal(lge.get(Lge.g))*/
            TypedQuery<ListGroceryEntity> query = entityManager.createQuery(q);

            listGroceryEntities =  query.getResultList();

            for (ListGroceryEntity ge:listGroceryEntities){
                listGroceryList.add(convert(ge));
            }
        }
        catch (Exception e){
            logger.error("cant getListById",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно получить записи из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return listGroceryList;
    }

    @Override
    public List<ListGrocery> getListByGroceryId(UUID id) throws ListGroceryException {
        List<ListGroceryEntity> listGroceryEntities;
        List<ListGrocery> listGroceryList = new ArrayList<>();
        EntityManager entityManager = factory.createEntityManager();

        try {

            //EntityType<ListGroceryEntity> Lge = entityManager.getMetamodel().entity(ListGroceryEntity.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ListGroceryEntity> q = criteriaBuilder.createQuery(ListGroceryEntity.class);
            Root<ListGroceryEntity> lge = q.from(ListGroceryEntity.class);

            q.select(lge).where(criteriaBuilder.equal(lge.get("groceryid"),id.toString()));

            /*Predicate condition = criteriaBuilder.equal(lge.get(Lge.g))*/
            TypedQuery<ListGroceryEntity> query = entityManager.createQuery(q);

            listGroceryEntities =  query.getResultList();

            for (ListGroceryEntity ge:listGroceryEntities){
                listGroceryList.add(convert(ge));
            }
        }
        catch (Exception e){
            logger.error("cant getListByGroceryId",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно получить записи из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return listGroceryList;
    }
}
