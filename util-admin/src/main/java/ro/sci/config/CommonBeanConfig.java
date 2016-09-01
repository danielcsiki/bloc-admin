/**
 * 
 */
package ro.sci.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author luff
 *
 */
@Configuration
@EnableJpaRepositories("ro.sci.repositories")
public class CommonBeanConfig {

}
