package com.remember5.system.modules.login;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.remember5.system.modules.security.service.UserDetailsServiceImpl;
import com.remember5.system.modules.system.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.remember5.system.modules.security.service.UserDetailsServiceImpl.executor;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginCacheTests {

    @Resource(name = "userDetailsService")
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void testCache() {
        long start1 = System.currentTimeMillis();
        int size = 10000;
        for (int i = 0; i < size; i++) {
            userDetailsService.loadUserByUsername("admin");
        }
        long end1 = System.currentTimeMillis();
        //关闭缓存
        userDetailsService.setEnableCache(false);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            userDetailsService.loadUserByUsername("admin");
        }
        long end2 = System.currentTimeMillis();
        System.out.print("使用缓存：" + (end1 - start1) + "毫秒\n 不使用缓存：" + (end2 - start2) + "毫秒");
    }

    @Test
    public void testExcel(HttpServletResponse response) throws IOException {
        List<User> rows = CollUtil.newArrayList(new User());
        //通过工具类创建writer
        // ExcelWriter writer = ExcelUtil.getWriter("d:/z/writeMapTest.xlsx");
        ExcelWriter writer = ExcelUtil.getWriter();
        //一次性写出内容，强制输出标题
        writer.write(rows, true);

        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition","attachment;filename=template.xls");
        ServletOutputStream out=response.getOutputStream();

        //out为OutputStream，需要写出到的目标流
        writer.flush(out, true);

        //关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    @Test
    public void testCacheManager() throws InterruptedException {
        int size = 1000;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            int mod = i % 10;
            executor.submit(() -> {
                try {
                    Thread.sleep(mod * 2 + (int) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userDetailsService.loadUserByUsername("admin" + mod);
                latch.countDown();
                System.out.println("剩余未完成数量" + latch.getCount());
            });
        }
        latch.await();
    }
}
