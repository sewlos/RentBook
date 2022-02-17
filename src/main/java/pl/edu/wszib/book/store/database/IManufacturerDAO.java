package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface IManufacturerDAO {

    Optional<Manufacturer> getManufacturerById(int mId);

    List<Manufacturer> getManufacturerByType(String mType);

    void addManufacturer(Manufacturer manufacturer);

    void deleteManufacturer(Manufacturer manufacturer);
}
