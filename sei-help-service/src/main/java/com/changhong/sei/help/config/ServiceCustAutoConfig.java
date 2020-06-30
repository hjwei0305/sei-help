package com.changhong.sei.help.config;

import com.changhong.sei.help.service.cust.BasicIntegration;
import com.changhong.sei.help.service.cust.BasicIntegrationCustBase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 实现功能：自定义业务逻辑扩展配置
 *
 * @author 马超(Vision.Mac)
 * @version 1.0.00  2020-06-30 08:53
 */
@Configuration
public class ServiceCustAutoConfig {

    /**
     * 自定义业务逻辑扩展配置
     *
     * @return 扩展实现
     */
    @Bean
    @ConditionalOnMissingBean(BasicIntegration.class)
    public BasicIntegration basicIntegration() {
        return new BasicIntegrationCustBase();
    }

}
