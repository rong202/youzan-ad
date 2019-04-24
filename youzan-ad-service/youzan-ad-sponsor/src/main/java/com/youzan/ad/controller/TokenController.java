package com.youzan.ad.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by baimugudu on 2019/4/23
 */

@Controller
public class TokenController {
/*@Autowired
Userservice userservice;

    public Map login(String username, String password){
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
         //为了方便测试，我们将过期时间设置为1分钟         
         long now = System.currentTimeMillis();//当前时间         
          long exp = now + 1000*60;//过期时间为1分钟

        //根据username 和 password 去数据库里面查询，返回user对象
        User user = userservice.getUser(username,password);
        if(null!=user){//登录成功
            objectObjectHashMap.put("code","SUCCESS");
            JwtBuilder builder = Jwts.builder().setId(user.getId).setSubject(user.getUsername)
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256,"mypassword")
                    .setExpiration(new Date(exp));
            System.out.println(builder.compact());
            objectObjectHashMap.put("token",builder.compact());
        }*/

      //  return objectObjectHashMap;

   // }
}
