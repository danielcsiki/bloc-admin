/**
 * 
 */
package ro.sci.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author luff
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("ro.sci")
@EnableJpaRepositories("ro.sci.repositories")
public class JpaIntegrationConfig {

}
