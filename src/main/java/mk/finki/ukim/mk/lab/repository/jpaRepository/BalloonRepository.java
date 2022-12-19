package mk.finki.ukim.mk.lab.repository.jpaRepository;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon,Long> {

    List<Balloon> findAllByNameOrDescription(String text,String t);

    Balloon findFirstByName(String name);

    void deleteById(Long id);


}
