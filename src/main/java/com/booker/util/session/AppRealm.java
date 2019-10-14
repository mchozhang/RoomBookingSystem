package com.booker.util.session;

import com.booker.domain.Customer;
import com.booker.domain.Staff;
import com.booker.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class AppRealm extends JdbcRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        String username = userPassToken.getUsername();
        User user = User.getUserByName(username);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roles = new HashSet<>();
        if (principals.isEmpty()) {
            return null;
        }
        User user = User.getUserByName((String)principals.getPrimaryPrincipal());
        if (user == null) {
            return null;
        }
        if (user instanceof Customer) {
            roles.add(AppSession.CUSTOMER_ROLE);
        } else if (user instanceof Staff) {
            roles.add(AppSession.STAFF_ROLE);
        }
        return new SimpleAuthorizationInfo(roles);
    }
}
