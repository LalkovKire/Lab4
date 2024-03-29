package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> findAll(){
        return DataHolder.manufacturerList;
    }

    public Optional<Manufacturer> findById(Long id) {
        return DataHolder.manufacturerList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

}
