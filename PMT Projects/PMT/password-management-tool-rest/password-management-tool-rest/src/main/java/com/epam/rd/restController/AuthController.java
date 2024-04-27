package com.epam.rd.restController;

import com.epam.rd.utils.JwtTokenUtil;
import com.epam.rd.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @PostMapping(value = "login" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String , String> login(@RequestBody UserBean userBean){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userBean.getUserName(), userBean.getPassword()));
        String token = jwtTokenUtil.generateToken(authentication.getName());
        Map <String , String > map = new HashMap<>();
        map.put("jwt",token);
        return map;
    }
}
