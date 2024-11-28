package jp.co.demo.controller;

import jp.co.demo.config.JwtUtils;
import jp.co.demo.dto.RespInfo;
import jp.co.demo.model.LoginForm;
import jp.co.demo.model.UserModel;

import jp.co.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1")
//@EnableAspectJAutoProxy
public class DemoController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RespInfo> postLogin(@RequestBody LoginForm form){
        log.debug("");

        RespInfo respInfo = new RespInfo();
        respInfo.setResult_code(HttpStatus.OK.value());
        respInfo.setResult_error("");

        String userId = form.getEmail();
        String userPsw = form.getPassword();

        JwtUtils jwtUtils = new JwtUtils();
        String jwtStr = jwtUtils.generateToken(form);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("jwt",jwtStr);

        Map<?,?> userMap = jwtUtils.extractAllClaims(jwtStr);

        String userName1 = (String)userMap.get("userName");
        String userPsw1 = (String)userMap.get("userPsw");

        respInfo.setResult(resultMap);

        return ResponseEntity.ok(respInfo);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getUsers(){
        log.debug("");
        List<UserModel> model=  userService.findUsers();
        return ResponseEntity.ok(model);
    }

    @GetMapping("/user")
    public ResponseEntity<UserModel> getUserById(@RequestParam String userId){
        log.debug("userId: {}", userId);
        UserModel model=  userService.getLoginUser(userId);
        return ResponseEntity.ok(model);
    }

}