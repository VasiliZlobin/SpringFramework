package ru.vasili_zlobin.springsecurity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasili_zlobin.springsecurity.model.Role;
import ru.vasili_zlobin.springsecurity.model.Rule;
import ru.vasili_zlobin.springsecurity.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found or wrong password", username)));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Collection<Role> roles = user.getUserRoles();
        List<Rule> rules = getRulesForRole(roles);
        Collection<GrantedAuthority> result = rules.stream()
                .map(rule -> new SimpleGrantedAuthority(rule.getName())).collect(Collectors.toList());
        roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).forEachOrdered(result::add);
        return result;
    }

    private List<Rule> getRulesForRole(Collection<Role> roles) {
        List<Rule> result = new ArrayList<>();
        for (Role role : roles) {
            List<Rule> currentRules = role.getRules();
            for (Rule currentRule : currentRules) {
                if (!result.contains(currentRule)) {
                    result.add(currentRule);
                }
            }
        }
        return result;
    }
}
