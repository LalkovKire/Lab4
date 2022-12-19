package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {

    public List<Balloon> findAllBalloons() {
      return DataHolder.balloonList;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
          if (text.equals("qqq")) return DataHolder.balloonList;
          else {
              return DataHolder.balloonList.stream().filter(r->r.getName().contains(text)).collect(Collectors.toList());
          }
    }

    public void deleteByID(Long id){
        DataHolder.balloonList.removeIf(r->r.getId().equals(id));
    }

    public Optional<Balloon> findById(Long id) {
        return DataHolder.balloonList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Balloon> save(String name,String description,Manufacturer manufacturer) {
        DataHolder.balloonList.removeIf(i -> i.getName().equals(name));
        Balloon product = new Balloon(name,description, manufacturer);
        DataHolder.balloonList.add(product);
        return Optional.of(product);
    }
}
