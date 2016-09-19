/**
 * 
 */
package ro.sci.services.security;

/**
 * @author yellow
 *
 */
public interface EncryptionService {

	String encryptString(String input);

	boolean checkPassword(String plainPassword, String encryptedPassword);

}
