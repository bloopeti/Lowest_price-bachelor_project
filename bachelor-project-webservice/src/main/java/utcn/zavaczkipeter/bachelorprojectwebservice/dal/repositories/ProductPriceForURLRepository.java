package utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductPriceForURL;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductPriceForURLRepository extends JpaRepository<ProductPriceForURL, Integer> {
}
