package pl.edu.wszib.book.store.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.database.IManufacturerDAO;
import pl.edu.wszib.book.store.model.Manufacturer;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class IManufacturerDAOImpl implements IManufacturerDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public Optional<Manufacturer> getManufacturerById(int mId) {
        Session session = this.sessionFactory.openSession();
        Query<Manufacturer> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Manufacturer WHERE id = :id");
        query.setParameter("id", mId);
        try {
            Manufacturer manufacturer = query.getSingleResult();
            session.close();
            return Optional.of(manufacturer);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public List<Manufacturer> getManufacturerByType(String mType) {
        Session session = this.sessionFactory.openSession();
        Query<Manufacturer> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Manufacturer WHERE type = :mType");
        query.setParameter("mType", Manufacturer.Type.valueOf(mType));

        List<Manufacturer> result = query.getResultList();
        session.close();
        return result;

    }

    @Override
    public void addManufacturer(Manufacturer manufacturer) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(manufacturer);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteManufacturer(Manufacturer manufacturer) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(manufacturer);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
