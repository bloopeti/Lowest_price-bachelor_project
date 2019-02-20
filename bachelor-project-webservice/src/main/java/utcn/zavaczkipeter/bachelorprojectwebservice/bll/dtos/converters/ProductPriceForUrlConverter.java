package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters;

import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductPriceForUrlDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductPriceForUrl;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductUrl;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductPriceForUrlConverter {
    private static ProductPriceForUrlConverter thisInstance;

    public static synchronized ProductPriceForUrlConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new ProductPriceForUrlConverter();
        return thisInstance;
    }

    public ProductPriceForUrl dtoToEntity(ProductPriceForUrlDto dto) {
        ProductPriceForUrl entity = new ProductPriceForUrl();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setCurrency(dto.getCurrency());
        ProductUrl productUrl = new ProductUrl();
        productUrl.setId(dto.getProductUrlId());
        entity.setProductUrl(productUrl);
        return entity;
    }

    public List<ProductPriceForUrl> dtoListToEntityList(List<ProductPriceForUrlDto> dtoList) {
        List<ProductPriceForUrl> entityList = new ArrayList<ProductPriceForUrl>();
        for (ProductPriceForUrlDto dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }

    public ProductPriceForUrlDto entityToDto(ProductPriceForUrl entity) {
        ProductPriceForUrlDto dto = new ProductPriceForUrlDto();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setCurrency(entity.getCurrency());
        dto.setProductUrlId(entity.getProductUrl().getId());
        return dto;
    }

    public List<ProductPriceForUrlDto> entityListToDtoList(List<ProductPriceForUrl> entityList) {
        List<ProductPriceForUrlDto> dtoList = new ArrayList<ProductPriceForUrlDto>();
        for (ProductPriceForUrl entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
