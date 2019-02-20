package utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductDetails;

import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer> {
    Optional<ProductDetails> findByProduct(Product product);
}
