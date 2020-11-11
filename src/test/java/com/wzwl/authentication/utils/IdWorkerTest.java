package com.wzwl.authentication.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author huff
 * @date 2020/11/11 15:58
 */
@SpringBootTest
class IdWorkerTest {


    @Test
    public void test(){
        IdWorker worker = new IdWorker(1, 1, 1);
        for (int i = 0; i < 1000; i++) {
            System.out.println(worker.nextId());
        }
    }


}