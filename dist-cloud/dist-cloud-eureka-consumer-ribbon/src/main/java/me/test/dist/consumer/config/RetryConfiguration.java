package me.test.dist.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryListenerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryPolicyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

/**
 * @author yaowf
 * @createDate 2018-05-23 15:54
 */
@Configuration
public class RetryConfiguration {

    @Bean
    LoadBalancedRetryListenerFactory retryListenerFactory(){
      return new LoadBalancedRetryListenerFactory() {
          @Override
          public RetryListener[] createRetryListeners(String s) {
              return new RetryListener[]{
                        new RetryListener() {
                            @Override
                            public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
                                return false;
                            }

                            @Override
                            public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {

                            }

                            @Override
                            public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {

                            }
                        }


              };
          }
      };
    };
}
