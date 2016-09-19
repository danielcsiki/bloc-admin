/**
 * 
 */
package ro.sci.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yellow
 *
 */
@Service
public class EncryptionServiceImpl implements EncryptionService {

	private StrongPasswordEncryptor strongEncryptor;

	@Autowired
	public void setStrongEncryptor(StrongPasswordEncryptor strongEncryptor) {
		this.strongEncryptor = strongEncryptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.security.EncryptionService#encryptString(java.lang.
	 * String)
	 */
	@Override
	public String encryptString(String input) {
		return strongEncryptor.encryptPassword(input);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.security.EncryptionService#checkPassword(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public boolean checkPassword(String plainPassword, String encryptedPassword) {
		return strongEncryptor.checkPassword(plainPassword, encryptedPassword);
	}

}
