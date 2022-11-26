package pillihuaman.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pillihuaman.com.Service.UserService;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespUser;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RespBase<RespUser> respo = userService.getUserByMail(username);

		if (respo !=null) {
			if (respo.getPayload().getMail().equals(username)) {
				return new User(respo.getPayload().getMail(), respo.getPayload().getPassword(),
						new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);

		}
	}
}