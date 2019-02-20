package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters;

import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDetailsDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductDetails;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailsConverter {
    private static ProductDetailsConverter thisInstance;

    public static synchronized ProductDetailsConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new ProductDetailsConverter();
        return thisInstance;
    }

    public ProductDetails dtoToEntity(ProductDetailsDto dto) {
        ProductDetails entity = new ProductDetails();
        entity.setId(dto.getId());
        Product product = new Product();
        product.setId(dto.getProductId());
        entity.setProduct(product);
        entity.setBrand(dto.getBrand());
        return entity;
    }

    public List<ProductDetails> dtoListToEntityList(List<ProductDetailsDto> dtoList) {
        List<ProductDetails> entityList = new ArrayList<ProductDetails>();
        for (ProductDetailsDto dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }

    public ProductDetailsDto entityToDto(ProductDetails entity) {
        ProductDetailsDto dto = new ProductDetailsDto();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProduct().getId());
        dto.setBrand(entity.getBrand());
        return dto;
    }

    public List<ProductDetailsDto> entityListToDtoList(List<ProductDetails> entityList) {
        List<ProductDetailsDto> dtoList = new ArrayList<ProductDetailsDto>();
        for (ProductDetails entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
