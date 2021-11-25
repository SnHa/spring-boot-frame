package com.atsun.coreapi;

import com.atsun.coreapi.dao.*;
import com.atsun.coreapi.dto.ManagerPageDTO;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class ApplicationTests {
    public void test01(Map<String,Object> map, List<String> list){
        System.out.println("test01");
    }
    public Map<String,Integer> test02(){
        System.out.println("test02");
        return null;
    }


    @Test
    void test() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> forName = Class.forName("com.atsun.coreapi.po.Role");
        System.out.println(forName);
        Constructor<?> constructor = forName.getConstructor();
        System.out.println(constructor);

    }
    @Test
    void generic() throws NoSuchMethodException {
        Method test01 = ApplicationTests.class.getMethod("test01", Map.class,List.class);
        Type[] genericParameterTypes = test01.getGenericParameterTypes();
        for (Type genericParameterType  :genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType){
                Type [] actualTypeArguments=((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument:actualTypeArguments){
                    System.out.println(actualTypeArgument);
                }
            }
        }
    }


}
