package utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductPriceForUrl;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductPriceForUrlRepository extends JpaRepository<ProductPriceForUrl, Integer> {
}
