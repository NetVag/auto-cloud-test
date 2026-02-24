package org.zzf.stress;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.zzf.EngineApplication;
import org.zzf.service.common.FileService;

import java.util.concurrent.TimeUnit;

/**
 * @author 詹泽峰
 * @date 2026/02/24 13:00
 */
@SpringBootTest(classes = EngineApplication.class)
@Slf4j
public class FileTest {

    @Resource
    private FileService fileService;

    @Test
    public void tempFileUrlApi() {
        // ========== 修正：仅传入存储桶内的文件路径 ==========
        // 错误示例："http://106.55.254.162:9001/browser/bucket/a79315ad87354dbeb6bb7b224031484d.jmx"
        // 正确示例：存储桶内的文件路径（假设bucket是"browser"，则路径为"a79315ad87354dbeb6bb7b224031484d.jmx"）
        String remoteFilePath = "a79315ad87354dbeb6bb7b224031484d.jmx";
        int expiry = 60;
        TimeUnit timeUnit = TimeUnit.SECONDS;

        try {
            // 调用服务方法
            String tempUrl = fileService.getTempAccessFileUrl(remoteFilePath, expiry, timeUnit);

            // 校验结果合法性
            Assertions.assertNotNull(tempUrl, "临时链接不能为空");
            Assertions.assertTrue(tempUrl.startsWith("http"), "临时链接必须是合法的URL");

            // 清晰的日志输出
            log.info("✅ 获取临时链接成功！");
            log.info("文件路径：{}", remoteFilePath);
            log.info("过期时间：{} {}", expiry, timeUnit.name().toLowerCase());
            log.info("临时链接：{}", tempUrl);

        } catch (Exception e) {
            // 精准捕获异常，定位问题
            log.error("❌ 获取临时链接失败！", e);
            // 测试失败，明确抛出断言异常
            Assertions.fail("获取临时链接失败：" + e.getMessage());
        }
    }


}
