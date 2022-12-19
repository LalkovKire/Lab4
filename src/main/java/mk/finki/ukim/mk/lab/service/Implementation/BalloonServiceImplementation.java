package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.InMemoryManufacturerRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImplementation implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImplementation (BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {

        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        if (text == null || text.isEmpty()) throw new IllegalArgumentException();
        return balloonRepository.findAllByNameOrDescription(text,text);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }

    @Override
    public void deleteByID(Long id) {
        this.balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufacturer) {
        Manufacturer man = this.manufacturerRepository.findById(manufacturer).orElseThrow();
        Balloon balloon = this.balloonRepository.findFirstByName(name);
        if(balloon != null && !balloon.equals(null)) {
            this.balloonRepository.deleteById(balloon.getId());
        }
        return Optional.of(this.balloonRepository.save(new Balloon(name,description,man)));
    }
}
