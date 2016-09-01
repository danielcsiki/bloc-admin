/**
 * 
 */
package ro.sci.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luff
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("ro.sci")
public class JpaIntegrationConfig {

}
