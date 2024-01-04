package com.anthonycj.train.generator.server;

import com.anthonycj.train.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String toPath = "generator\\src\\main\\java\\com\\anthonycj\\train\\generator\\test\\";
    static {
        new File(toPath).mkdirs();
    }

    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("test.ftl");
        Map<String, Object> param = new HashMap<>();
        param.put("domain", "Test1");
        FreemarkerUtil.generator(toPath + "Test1.java", param);
    }
}
