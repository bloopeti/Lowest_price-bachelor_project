package utcn.zavaczkipeter.bachelorprojectwebservice.bll.crud;

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
            if (userDto.getPassword() != null)
                updatedUser.setPassword(userDto.getPassword());
            updatedUser.setIsAdmin(userDto.getIsAdmin());
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
            if (userDto.getPassword().equals(dbUser.getPassword()))
                return dbUser;
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
            userToRegister.setPassword(userDto.getPassword());
            userToRegister.setTrackedProducts(new ArrayList<ProductDto>());
            addUser(userDto);
            return "USER REGISTER SUCCESSFUL";
        }
        return "USER REGISTER FAILED: An account already exists with this email address!";
    }
}
