package utcn.zavaczkipeter.bachelorprojectwebservice.bll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.crud.UserBll;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.UserDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.wrappers.IdWrapper;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserBll userBll;

    @GetMapping(value = "/getAll")
    public List<UserDto> getAllUsers() {
        return userBll.getAllUsers();
    }

    @GetMapping(value = "/get/{id}")
    public UserDto getUserById(@PathVariable("id") int id) {
        return userBll.getUserById(id);
    }

    @PostMapping(value = "/add")
    public String addUser(@RequestBody UserDto user) {
        return userBll.addUser(user);
    }

    @PostMapping(value = "/update")
    public String updateUser(@RequestBody UserDto user) {
        return userBll.updateUser(user);
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestBody IdWrapper idWrapper) {
        return userBll.deleteUser(idWrapper.getId());
    }
}