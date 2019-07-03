package utcn.zavaczkipeter.bachelorprojectwebservice.bll.operations;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.UserDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.UserConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.User;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBll {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    ProductBll productBll;

    public List<UserDto> getAllUsers() {
        return userConverter.entityListToDtoList(userRepository.findAll());
    }

    public UserDto getUserById(int id) {
        if (userRepository.findById(id).isPresent())
            return userConverter.entityToDto(userRepository.findById(id).get());
        else
            return null;
    }

    public UserDto getUserByEmailAddress(UserDto userDto) {
        if (userRepository.findByEmailAddress(userDto.getEmailAddress()).isPresent())
            return userConverter.entityToDto(userRepository.findByEmailAddress(userDto.getEmailAddress()).get());
        else
            return null;
    }

    public String addUser(UserDto userDto) {
        userRepository.save(userConverter.dtoToEntity(userDto));
        return "USER ADD SUCCESSFUL";
    }

    public String updateUser(UserDto userDto) {
        User updatedUser;
        String reason = "User";
        if (userRepository.findById(userDto.getId()).isPresent()) {
            updatedUser = userRepository.findById(userDto.getId()).get();
            if (userDto.getEmailAddress() != null)
                updatedUser.setEmailAddress(userDto.getEmailAddress());
            if (userDto.getPassNoHash() != null) {
                updatedUser.setPassNoHash(userDto.getPassNoHash());
                updatedUser.setPassword(DigestUtils.sha512Hex(userDto.getPassNoHash()));
            }
            updatedUser.setIsAdmin(userDto.getIsAdmin());
            User passedUser = userConverter.dtoToEntity(userDto);
            updatedUser.setFavouriteProducts(passedUser.getFavouriteProducts());
            userRepository.save(updatedUser);
            return "USER UPDATE SUCCESSFUL";
        }
        return "USER UPDATE FAILED: " + reason + " with this ID doesn't exist";
    }

    public String deleteUser(int id) {
        String reason = "User";
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return "USER DELETE SUCCESSFUL";
        }
        return "USER DELETE FAILED: " + reason + "with this ID doesn't exist!";
    }

    public UserDto login(UserDto userDto) {
        UserDto dbUser = getUserByEmailAddress(userDto);
        if (!(dbUser == null)) {
            if (DigestUtils.sha512Hex(userDto.getPassNoHash()).equals(dbUser.getPassword())) {
                dbUser.setPassword(null);
                dbUser.setPassNoHash(null);
                return dbUser;
            }
        }
        dbUser = new UserDto();
        dbUser.setIsAdmin(-10);
        return dbUser;
    }

    public String register(UserDto userDto) {
        UserDto userToRegister = getUserByEmailAddress(userDto);
        if (userToRegister == null) {
            userToRegister = new UserDto();
            userToRegister.setIsAdmin(0);
            userToRegister.setEmailAddress(userDto.getEmailAddress());
            if (userDto.getPassNoHash().equals(userDto.getPassNoHashRepeat())) {
                userToRegister.setPassNoHash(userDto.getPassNoHash());
                userToRegister.setPassword(DigestUtils.sha512Hex(userDto.getPassNoHash()));
                userToRegister.setFavouriteProducts(new ArrayList<ProductDto>());
                addUser(userToRegister);
                return "USER REGISTER SUCCESSFUL";
            } else
                return "USER REGISTER FAILED: Passwords don't match";
        }
        return "USER REGISTER FAILED: An account already exists with this email address!";
    }

    // the userDto parameter should only contain the ID of the user
    // and one single element in the list of the favourite products,
    // containing the id of the product which should be added to the favourites list
    public String addProductToFavourites(UserDto userDto) {
        UserDto dbUser = getUserById(userDto.getId());
        String reason = "Invalid User ID";
        if (dbUser != null) {
            ProductDto product;
            reason = "No product to insert (empty list passed)";
            if (!(userDto.getFavouriteProducts().isEmpty())) {
                product = productBll.getProductById(userDto.getFavouriteProducts().get(0).getId());
                reason = "Product with this ID doesn't exist";
                if (product != null) {
                    int indexOfProduct = -1;
                    List<ProductDto> productsInFavorites = dbUser.getFavouriteProducts();
                    for (ProductDto productDto : productsInFavorites) {
                        if (productDto.getId() == userDto.getFavouriteProducts().get(0).getId())
                            indexOfProduct = productsInFavorites.indexOf(productDto);
                    }
                    reason = "This product is already in the favourites";
                    if (indexOfProduct == -1) {
                        dbUser.getFavouriteProducts().add(product);
                        reason = updateUser(dbUser);
                        if (reason.contains("SUCCESSFUL")) {
                            return "PRODUCT INSERTION TO FAVOURITES SUCCESSFUL";
                        }
                    }
                }
            }
        }
        return "PRODUCT INSERTION TO FAVOURITES FAILED: " + reason;
    }

    // the userDto parameter should only contain the ID of the user
    // and one single element in the list of the favourite products,
    // containing the id of the product which should be removed from the favourites list
    public String removeProductFromFavourites(UserDto userDto) {
        UserDto dbUser = getUserById(userDto.getId());
        String reason = "Invalid User ID";
        if (dbUser != null) {
            ProductDto product;
            reason = "No product to remove (empty list passed)";
            if (!(userDto.getFavouriteProducts().isEmpty())) {
                product = productBll.getProductById(userDto.getFavouriteProducts().get(0).getId());
                reason = "Product with this ID doesn't exist";
                if (product != null) {
                    reason = "Product isn't favourited by this user";
                    List<ProductDto> productsInFavorites = dbUser.getFavouriteProducts();
                    int indexToRemove = -1;
                    for (ProductDto productDto : productsInFavorites) {
                        if (productDto.getId() == userDto.getFavouriteProducts().get(0).getId())
                            indexToRemove = productsInFavorites.indexOf(productDto);
                    }
                    if (indexToRemove != -1) {
                        dbUser.getFavouriteProducts().remove(indexToRemove);
                        reason = updateUser(dbUser);
                        if (reason.contains("SUCCESSFUL")) {
                            return "PRODUCT REMOVAL FROM FAVOURITES SUCCESSFUL";
                        }
                    }
                }
            }
        }
        return "PRODUCT REMOVAL FROM FAVOURITES FAILED: " + reason;
    }

}
