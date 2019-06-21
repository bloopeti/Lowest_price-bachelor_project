package utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;

import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<List<Product>> findByNameContains(String searchQuery);
}
