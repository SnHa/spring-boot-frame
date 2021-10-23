package com.atsun.coreapi;

import com.atsun.coreapi.dao.ComplexDao;
import com.atsun.coreapi.dao.ManagerSimpleDao;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class ApplicationTests {


    @Autowired
    private ManagerSimpleDao managerSimpleDao;

    @Autowired
    public void setManagerSimpleDao(ManagerSimpleDao managerSimpleDao) {
        this.managerSimpleDao = managerSimpleDao;
    }

    @Test
    void contextLoads() {

        //String sql = "SELECT * FROM t_manager tm WHERE tm.username=:username";

        String sql="select * from t_manager tm where tm.username=?";
        HashMap<String, Object> params = new HashMap<>();
        params.put("username","sunhao");

        ManagerVO vo = managerSimpleDao.getUserSql("sunhao");

        System.out.println(vo);
    }
}
