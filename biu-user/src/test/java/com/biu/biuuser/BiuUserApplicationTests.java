package com.biu.biuuser;

import com.biu.mapper.SysMenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@SpringBootTest
class BiuUserApplicationTests {
    @Resource
    private SysMenuMapper menuMapper;


    @Test
    void test() {
        List<String> permsByUserId = menuMapper.getPermsByUserId(2L);
        System.out.println(permsByUserId);
    }
}
