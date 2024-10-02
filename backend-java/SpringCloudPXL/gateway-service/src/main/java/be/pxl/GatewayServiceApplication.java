package be.pxl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Gateway Service (Rabbit MQ)
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
