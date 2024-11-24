package jp.co.demo.controller;

import jp.co.demo.repository.UserModel;
import jp.co.demo.repository.UsersModel;
import jp.co.demo.service.ApiSampleService;
import jp.co.demo.repository.ApiSampleModel;

import jp.co.demo.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//@Controller
@RestController
@RequestMapping("/v1")
//@EnableAspectJAutoProxy
public class DemoController {

    @Autowired
    UsersService usersService;

    //
    @PostMapping("/login")
    public ResponseEntity<RespInfo> postLogin(@RequestBody LoginForm form){
        log.debug("");

        RespInfo respInfo = new RespInfo();
        respInfo.setEmail(form.getEmail());
        respInfo.setPassword(form.getPassword());
        respInfo.setToken("TestToken");

        return ResponseEntity.ok(respInfo);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getUsers(){
        log.debug("");
        List<UserModel> model=  usersService.findUsers();
        return ResponseEntity.ok(model);
    }

    @GetMapping("/user")
    public ResponseEntity<UserModel> getUserById(@RequestParam String userId){
        log.debug("userId: {}", userId);
        UserModel model=  usersService.findByUserId(userId);
        return ResponseEntity.ok(model);
    }

}