package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductUrlDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductUrl;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductConverter {
    @Autowired
    private ProductDetailsConverter productDetailsConverter;
    @Autowired
    private ProductUrlConverter productUrlConverter;
    private static ProductConverter thisInstance;

    public static synchronized ProductConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new ProductConverter();
        return thisInstance;
    }

    public Product dtoToEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setProductDetails(productDetailsConverter.dtoToEntity(dto.getProductDetails()));
        entity.setUrls(new ArrayList<ProductUrl>());
        List<ProductUrlDto> productUrlDtoList = dto.getUrls();
        if (productUrlDtoList != null) {
            for (ProductUrlDto productUrlDto : productUrlDtoList)
                entity.getUrls().add(productUrlConverter.dtoToEntity(productUrlDto));
        }
        return entity;
    }

    public List<Product> dtoListToEntityList(List<ProductDto> dtoList) {
        List<Product> entityList = new ArrayList<Product>();
        for (ProductDto dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }

    public ProductDto entityToDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setProductDetails(productDetailsConverter.entityToDto(entity.getProductDetails()));
        dto.setUrls(new ArrayList<ProductUrlDto>());
        List<ProductUrl> productUrlList = entity.getUrls();
        if (productUrlList != null) {
            for (ProductUrl productUrl : productUrlList)
                dto.getUrls().add(productUrlConverter.entityToDto(productUrl));
        }
        return dto;
    }

    public List<ProductDto> entityListToDtoList(List<Product> entityList) {
        List<ProductDto> dtoList = new ArrayList<ProductDto>();
        for (Product entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
