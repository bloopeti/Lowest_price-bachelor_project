package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.UserDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConverter {
    @Autowired
    private ProductConverter productConverter;
    private static UserConverter thisInstance;

    public static synchronized UserConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new UserConverter();
        return thisInstance;
    }

    @SuppressWarnings("Duplicates")
    public User dtoToEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmailAddress(dto.getEmailAddress());
        entity.setPassword(dto.getPassword());
        entity.setPassNoHash(dto.getPassNoHash());
        entity.setIsAdmin(dto.getIsAdmin());
        entity.setTrackedProducts(new ArrayList<Product>());
        List<ProductDto> productDtoList = dto.getTrackedProducts();
        if (productDtoList != null) {
            for (ProductDto productDTO : productDtoList)
                entity.getTrackedProducts().add(productConverter.dtoToEntity(productDTO));
        }
        return entity;
    }

    public List<User> dtoListToEntityList(List<UserDto> dtoList) {
        List<User> entityList = new ArrayList<User>();
        for (UserDto dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }

    @SuppressWarnings("Duplicates")
    public UserDto entityToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmailAddress(entity.getEmailAddress());
        dto.setPassword(entity.getPassword());
        dto.setPassNoHash(entity.getPassNoHash());
        dto.setIsAdmin(entity.getIsAdmin());
        dto.setTrackedProducts(new ArrayList<ProductDto>());
        List<Product> productList = entity.getTrackedProducts();
        if (productList != null) {
            for (Product product : productList)
                dto.getTrackedProducts().add(productConverter.entityToDto(product));
        }
        return dto;
    }

    public List<UserDto> entityListToDtoList(List<User> entityList) {
        List<UserDto> dtoList = new ArrayList<UserDto>();
        for (User entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
