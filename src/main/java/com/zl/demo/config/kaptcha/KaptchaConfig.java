package com.zl.demo.config.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 验证码样式设置
 * @author zy
 * @version $Revision$ 2020年
 */
@Component
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDDefaultKaptcha() {
        DefaultKaptcha dk = new DefaultKaptcha();
        Properties properties = new Properties();

        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "red");
        // 文本集合，验证码值从此集合中获取 ( abcde2345678gfynmnpwx )
        properties.setProperty("kaptcha.textproducer.char.string", "23456789");
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "48");
        // session key
        properties.setProperty("kaptcha.session.key", "code");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");

        Config config = new Config(properties);
        dk.setConfig(config);

        return dk;
    }
}
