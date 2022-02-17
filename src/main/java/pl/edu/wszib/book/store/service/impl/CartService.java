package pl.edu.wszib.book.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.database.InItemDAO;
import pl.edu.wszib.book.store.model.Item;
import pl.edu.wszib.book.store.model.OrderPosition;
import pl.edu.wszib.book.store.service.ICartService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    InItemDAO bookDAO;

    @Resource
    SessionObject sessionObject;

    public void addBookToCart(int bookId) {
        Optional<Item> bookBox = this.bookDAO.getItemById(bookId);

        if(bookBox.isEmpty()) {
            return;
        }

        Item item = bookBox.get();
        if(item.getQuantity() <= 0) {
            return;
        }

        for(OrderPosition orderPosition : this.sessionObject
                .getCart().getOrderPositions()) {
            if(orderPosition.geItem().getId() == bookId) {
                orderPosition.incrementQuantity();
                return;
            }
        }

        OrderPosition orderPosition = new OrderPosition(0, item, 1);
        this.sessionObject.getCart().getOrderPositions().add(orderPosition);
    }
}
