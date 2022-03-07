package com.bolin.logistics;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//获取到配置文件并解析,都是固定代码,copy即可
public class Generate {
    public static void main(String[] args) {
        List<String> warings = new ArrayList<String>();
        boolean overwrite = true;
        String genCig = "/generatorConfig.xml"; //获取到配置文件
        File configFile = new File(Objects.requireNonNull(Generate.class.getResource(genCig)).getFile());
        ConfigurationParser configurationParser = new
                ConfigurationParser(warings);
        Configuration configuration = null;
        try {
            configuration = configurationParser.parseConfiguration(configFile);
        } catch (IOException | XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            if (configuration != null) {
                myBatisGenerator = new
                        MyBatisGenerator(configuration, callback, warings);
            }
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            if (myBatisGenerator != null) {
                myBatisGenerator.generate(null);
            }
        } catch (SQLException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
