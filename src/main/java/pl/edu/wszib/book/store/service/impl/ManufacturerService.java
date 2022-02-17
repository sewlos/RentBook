package pl.edu.wszib.book.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.database.IManufacturerDAO;
import pl.edu.wszib.book.store.model.Manufacturer;
import pl.edu.wszib.book.store.service.IManufacturerService;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService implements IManufacturerService {

    @Autowired
    IManufacturerDAO manufacturerDAO;

    @Override
    public Optional<Manufacturer> getManufacturerById(int bookId) {
        return this.manufacturerDAO.getManufacturerById(bookId);
    }

    @Override
    public void addManufacturer(Manufacturer manufacturer) {
        this.manufacturerDAO.addManufacturer(manufacturer);
    }

    @Override
    public List<Manufacturer> getManufacturerByType(String type) {
        return this.manufacturerDAO.getManufacturerByType(type);
    }
}
