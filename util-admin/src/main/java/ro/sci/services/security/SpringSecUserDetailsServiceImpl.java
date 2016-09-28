/**
 * 
 */
package ro.sci.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ro.sci.converters.UserToUserDetails;
import ro.sci.services.UserService;

/**
 * @author yellow
 *
 */
@Service("userDetailsService")
public class SpringSecUserDetailsServiceImpl implements UserDetailsService {

	private UserService userService;
	private UserToUserDetails userToUserDetails;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	@Qualifier(value = "userToUserDetails")
	public void setUserToUserDetails(UserToUserDetails userToUserDetails) {
		this.userToUserDetails = userToUserDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userToUserDetails.convert(userService.findByUsername(username));
	}

}
