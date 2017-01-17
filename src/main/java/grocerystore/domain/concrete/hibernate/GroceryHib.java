package grocerystore.domain.concrete.hibernate;

import grocerystore.domain.abstracts.IRepositoryGrocery;
import grocerystore.domain.entityes.GroceriesEntity;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.GroceryException;
import grocerystore.domain.models.Grocery;
import grocerystore.services.abstracts.IGroceryService;
import grocerystore.services.exceptions.FormGroceryException;
import grocerystore.services.exceptions.GroceryServiceException;
import grocerystore.tools.HibernateUtil;
import grocerystore.tools.JPAUtil;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 17.01.2017.
 */
@Repository
public class GroceryHib extends HibImplementation implements IRepositoryGrocery {
    private static final Logger logger = LoggerFactory.getLogger(GroceryHib.class);

    private Grocery convert(GroceriesEntity ge){
        Grocery grocery = new Grocery();
        grocery.setId(UUID.fromString(ge.getId()));
        grocery.setName(ge.getName());
        grocery.setPrice(ge.getPrice());
        grocery.setParentid(UUID.fromString(ge.getParentid()));
        grocery.setIscategory(ge.getIscategory()!=0);
        grocery.setQuantity(ge.getQuantity());
        return grocery;
    }

    private GroceriesEntity convert(Grocery g){
        GroceriesEntity groceriesEntity = new GroceriesEntity();
        groceriesEntity.setId(g.getId().toString());
        groceriesEntity.setName(g.getName());
        groceriesEntity.setPrice(g.getPrice());
        groceriesEntity.setParentid(g.getParentid().toString());
        groceriesEntity.setIscategory(!g.isIscategory() ? (byte) 0 : (byte) 1);
        groceriesEntity.setQuantity(g.getQuantity());
        return groceriesEntity;
    }

    @Override
    public List<Grocery> getAll() throws GroceryException {
        List<GroceriesEntity> groceriesEntityList;
        List<Grocery> groceryList = new ArrayList<>();
        EntityManager entityManager = factory.createEntityManager();
        //EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<GroceriesEntity> q = criteriaBuilder.createQuery(GroceriesEntity.class);
            Root<GroceriesEntity> root = q.from(GroceriesEntity.class);
            TypedQuery<GroceriesEntity> query = entityManager.createQuery(q);

            groceriesEntityList =  query.getResultList();

            for (GroceriesEntity ge:groceriesEntityList){
                groceryList.add(convert(ge));
            }
        }
        catch (Exception e){
            logger.error("cant getAll",e);
            throw new GroceryException("Проблема с базой данных: невозможно получить записи из таблицы продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return groceryList;
    }

    @Override
    public Grocery getOne(UUID id) throws GroceryException {
        Grocery grocery;
        EntityManager entityManager = factory.createEntityManager();

        try {
            GroceriesEntity groceriesEntity = entityManager.find(GroceriesEntity.class, id.toString());
            grocery = convert(groceriesEntity);
        }
        catch (Exception e){
            logger.error("Cant getOne Grocery!", e);
            throw new GroceryException("Проблема с базой данных: невозможно получить запись из таблицы продуктов!",e);
        }
        finally {
            entityManager.close();
        }


        return grocery;
    }

    @Override
    public boolean create(Grocery entity) throws GroceryException {
        GroceriesEntity groceriesEntity = convert(entity);
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(groceriesEntity);

            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant create",e);
            throw new GroceryException("Проблема с базой данных: невозможно создать запись в таблице продуктов!",e);
        }
        finally {
            entityManager.close();
        }


        return true;
    }

    @Override
    public boolean delete(UUID id) throws GroceryException {
        EntityManager entityManager = factory.createEntityManager();

        GroceriesEntity groceriesEntity = entityManager.find(GroceriesEntity.class, id.toString());

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(groceriesEntity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant delete",e);
            throw new GroceryException("Проблема с базой данных: невозможно удалить запись из таблицы продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean update(Grocery entity) throws GroceryException {
        EntityManager entityManager = factory.createEntityManager();

        GroceriesEntity groceriesEntity = convert(entity);

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(groceriesEntity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant update",e);
            throw new GroceryException("Проблема с базой данных: невозможно изменить запись в таблице продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

   /* public static void main(String[] args) {
        try {
            List<Grocery> entities = new GroceryHib().getAll();
            for(Grocery g:entities){
                System.out.println(g.getName());
            }
        } catch (GroceryException e) {
            e.printStackTrace();
        }
    }*/

}
