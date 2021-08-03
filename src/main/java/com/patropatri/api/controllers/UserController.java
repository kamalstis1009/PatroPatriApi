package com.patropatri.api.controllers;

import com.patropatri.api.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    /*@Autowired
    private UserService mService;

    @PostMapping("/user/add")
    public void addUser(@RequestBody User item) {
        mService.addUser(item);
    }

    @GetMapping(value="/users" ,produces= {"application/xml"})
    public List<User> getAllUsers(){
        return mService.getAllUser();
    }

    @PutMapping(value="/user/update")
    public void updateUser(@RequestBody User item) {
        mService.updateUser(item);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") int id) {
        return mService.getUser(id);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        mService.deleteUser(id);
    }*/

}
