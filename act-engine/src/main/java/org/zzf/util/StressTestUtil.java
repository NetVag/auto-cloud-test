package org.zzf.util;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.util.JMeterUtils;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.io.File;

/**
 * jmeter测试工具类
 *
 * @author 詹泽峰
 * @date 2025/12/29 16:25
 */
public class StressTestUtil {

    /**
     * 获取jmeter的home路径
     * TODO: 后续部署上线后调整
     *
     * @return java.lang.String JMeter的home路径
     */
    public static String getJmeterHome() {
        try {
            // String path = StressTestUtil.class.getClassLoader().getResource("jmeter").getPath();
            String path = "E:\\NetNeko\\workspace\\project\\jmeter";
            return path;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取jmeter的bin目录
     *
     * @return java.lang.String JMeter的bin目录
     */
    public static String getJmeterHomeBin() {
        return getJmeterHome() + File.separator + "bin";
    }

    /**
     * 初始化jmeter配置文件
     *
     * @return
     */
    public static void initJmeterProperties() {
        String jmeterHome = getJmeterHome();
        String jmeterHomeBin = getJmeterHomeBin();

        // 加载jmeter配置文件
        JMeterUtils.loadJMeterProperties(jmeterHomeBin + File.separator + "jmeter.properties");

        // 设置JMeter安装目录
        JMeterUtils.setJMeterHome(jmeterHome);

        // 避免中文响应乱码
        JMeterUtils.setProperty("sampleresult.default.encoding", "UTF-8");

        // 初始化本地环境
        JMeterUtils.initLocale();
    }

    /**
     * 获取jmeter引擎
     *
     * @return org.apache.jmeter.engine.StandardJMeterEngine JMeter引擎
     */
    public static StandardJMeterEngine getJMeterEngine() {
        // 初始化配置
        initJmeterProperties();
        StandardJMeterEngine jMeterEngine = new StandardJMeterEngine();
        return jMeterEngine;
    }
}
