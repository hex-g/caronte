package hive.caronte.security;

import hive.caronte.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final var hiveUser = userRepository.findByUsername(username);

    if (hiveUser == null) {
      throw new UsernameNotFoundException("Username: " + username + " not found");
    }

    final var grantedAuthorities =
        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + hiveUser.getRole());

    // return ID instead of username
    return new User(hiveUser.getId().toString(), hiveUser.getPassword(), grantedAuthorities);
  }
}
