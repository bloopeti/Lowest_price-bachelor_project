package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductUrlDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductUrl;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductUrlConverter {
    @Autowired
    private ProductPriceForUrlConverter productPriceForUrlConverter;
    private static ProductUrlConverter thisInstance;

    public static synchronized ProductUrlConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new ProductUrlConverter();
        return thisInstance;
    }

    public ProductUrl dtoToEntity(ProductUrlDto dto) {
        ProductUrl entity = new ProductUrl();
        entity.setId(dto.getId());
        entity.setUrl(dto.getUrl());
        entity.setDomain(dto.getDomain());
        Product product = new Product();
        product.setId(dto.getId());
        entity.setProduct(product);
        entity.setProductPriceForUrl(productPriceForUrlConverter.dtoToEntity(dto.getProductPriceForUrl()));
        return entity;
    }

    public List<ProductUrl> dtoListToEntityList(List<ProductUrlDto> dtoList) {
        List<ProductUrl> entityList = new ArrayList<ProductUrl>();
        for (ProductUrlDto dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }

    public ProductUrlDto entityToDto(ProductUrl entity) {
        ProductUrlDto dto = new ProductUrlDto();
        dto.setId(entity.getId());
        dto.setUrl(entity.getUrl());
        dto.setDomain(entity.getDomain());
        dto.setProductId(entity.getProduct().getId());
        dto.setProductPriceForUrl(productPriceForUrlConverter.entityToDto(entity.getProductPriceForUrl()));
        return dto;
    }

    public List<ProductUrlDto> entityListToDtoList(List<ProductUrl> entityList) {
        List<ProductUrlDto> dtoList = new ArrayList<ProductUrlDto>();
        for (ProductUrl entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
