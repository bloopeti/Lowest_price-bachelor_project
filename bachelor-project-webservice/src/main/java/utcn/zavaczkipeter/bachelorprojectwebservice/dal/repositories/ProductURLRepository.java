package utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductUrl;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductURLRepository extends JpaRepository<ProductUrl, Integer> {
    ProductUrl findByProduct(Product product);
    ProductUrl findByDomain(String domain);
}
