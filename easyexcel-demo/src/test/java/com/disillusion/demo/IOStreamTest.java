package com.disillusion.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class IOStreamTest {
    @Test
    void testIO01() {
        // 输出项目目录
        String project_path = System.getProperty("user.dir");
        System.out.print(project_path);
        // 设定本地文件路径
        String file_path = project_path + "\\test01.txt";
        File file = new File(file_path);
        FileOutputStream os = null;
        BufferedOutputStream bw = null;
        String data = "Hello world!";
        try {
            os = new FileOutputStream(file);
            bw = new BufferedOutputStream(os);
            bw.write(data.getBytes());
            // for (int i = 0; i < data.length(); i++) {
            //     bw.write(data.charAt(i));
            // }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 可以只关闭处理流，不用关闭节点流，因为处理流关闭的时候，会调用节点流的关闭方法。
                // 流之间存在依赖关系：如果流a依赖流b，那么应该先关闭流a，再关闭流b。例如：处理流a依赖节点流b，应该先关闭处理流a，再关闭节点流b。
                // 流多次关闭并不会报异常。
                // 但如果先关闭节点流，再关闭处理流，会抛出IO异常；
                // 如果先关闭节点流，处理流依然可写，write不会报错，但是不能推到下一个流去，比如不能flush到节点流，因为节点流已经关闭，也不能close，因为close方法也会先flush到下一个流，所以close和flush都会报错。
                bw.close();
                // 结论：建议只关闭最外层的流，或者用try(Resources)定义流。
                // 只关闭节点流也是可以的，因为其他的流并不是真实和文件连接的，所以只需要关闭节点流，其他的流其实就是对象，等着被GC垃圾回收也可以。但是关闭节点流后，就不能再用处理流往节点流传东西了，否则会报错。
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}