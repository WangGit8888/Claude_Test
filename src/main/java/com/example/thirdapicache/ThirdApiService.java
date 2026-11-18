package com.example.thirdapicache;



import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ThirdApiService {

   static Map<String, String> map = new HashMap<>();

   static  {
       map.put("key1", "value1");
       map.put("key2", "value2");
       map.put("key3", "value3");
    }

    @ThirdApiCache(value = "enterprise:detail", ttl = 3600, jitter = 120)
    public String queryEnterprise(String creditCode) {
        return query(creditCode);
    }


    String query(String key){
        System.out.println("第三方接口取数:"+key);
       return map.get(key);
    }

}
