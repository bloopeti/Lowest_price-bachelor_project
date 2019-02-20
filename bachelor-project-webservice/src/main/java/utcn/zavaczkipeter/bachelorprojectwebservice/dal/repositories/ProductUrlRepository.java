package utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductUrl;

import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductUrlRepository extends JpaRepository<ProductUrl, Integer> {
    Optional<List<ProductUrl>> findByProduct(Product product);
    Optional<List<ProductUrl>> findByDomain(String domain);
}
