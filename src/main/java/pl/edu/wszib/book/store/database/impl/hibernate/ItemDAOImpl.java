package pl.edu.wszib.book.store.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.database.InItemDAO;
import pl.edu.wszib.book.store.model.Item;
import pl.edu.wszib.book.store.model.Manufacturer;
import pl.edu.wszib.book.store.service.IManufacturerService;

import javax.persistence.NoResultException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemDAOImpl implements InItemDAO {

    @Autowired
    Connection connection;


    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    IManufacturerService iManufacturerService;

    @Override
    public void addItem(Item item) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(item);
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
    public void deleteItem(Item item) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(item);
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
    public List<Item> getItems() {
        Session session = this.sessionFactory.openSession();
        Query<Item> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Item");
        List<Item> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Item> getItemById(int itemId) {
        Session session = this.sessionFactory.openSession();
        Query<Item> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Item WHERE id = :id");
        query.setParameter("id", itemId);
        try {
            Item item = query.getSingleResult();
            session.close();
            return Optional.of(item);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateItem(Item item) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(item);
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
    public List<Item> getItemsByType(String type) {
        Session session = this.sessionFactory.openSession();
        Query<Item> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Item where type = :type");
        query.setParameter("type", Item.Type.valueOf(type));
        List<Item> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public HashSet<Item> getItemsByManufacturer(int mId) {
        Session session = this.sessionFactory.openSession();
        Query<Item> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Item where manufacturer_id = :manufacturer_id");
        query.setParameter("manufacturer_id", mId);
        List<Item> result = query.getResultList();
        session.close();

        return new HashSet<>(result);
    }

    @Override
    public List<Item> getRandomItems() {

        List<Item> listItem = new ArrayList<>();

        try {
            String sql = "SELECT * FROM t_item ORDER BY RAND() LIMIT 2";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Item item = new Item();

                Optional<Manufacturer> manufacturer = this.iManufacturerService.getManufacturerById(rs.getInt("manufacturer_id"));

                Manufacturer manufacturer1;


                if (manufacturer.isEmpty())
                    manufacturer1 = new Manufacturer("temp name", Manufacturer.Type.valueOf("Author"));
                else
                    manufacturer1 = manufacturer.get();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                item.setType(Item.Type.valueOf(rs.getString("type")));
                item.setManufacturer(manufacturer1);
                item.setDescription(rs.getString("description"));


                listItem.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listItem;
    }

    @Override
    public Item getRandomItem() {

        Item item = new Item();
        try {
            String sql = "SELECT * FROM t_item ORDER BY RAND() LIMIT 1";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Optional<Manufacturer> manufacturer = this.iManufacturerService.getManufacturerById(rs.getInt("manufacturer_id"));

                Manufacturer manufacturer1;


                if (manufacturer.isEmpty())
                    manufacturer1 = new Manufacturer("temp name", Manufacturer.Type.valueOf("Author"));
                else
                   manufacturer1 = manufacturer.get();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                item.setType(Item.Type.valueOf(rs.getString("type")));
                item.setManufacturer(manufacturer1);
                item.setDescription(rs.getString("description"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return item;
    }
}
