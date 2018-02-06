package ua.lviv.courierdelivery.security.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.lviv.courierdelivery.model.enums.AuthorityName;
import ua.lviv.courierdelivery.security.JwtTokenUtil;
import ua.lviv.courierdelivery.security.JwtUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Apple on 06.02.2018.
 */
@Service
public class AuthenticationRestService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${app.jwt.header}")
    private String tokenHeader;

    public HttpHeaders getAuthHeaders(String email, String password, Device device) {
        Authentication authentication = getAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        validateUser(userDetails);
        String token = jwtTokenUtil.generateToken(userDetails, device);
        return addTokenToHeaderCookie(token);
    }

    public HttpHeaders getHeadersForRefreshToken(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        validateUser(user);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return addTokenToHeaderCookie(refreshedToken);
        } else {
            throw new BadCredentialsException("Token is not valid");
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(tokenHeader)) {
                    token = cookie.getValue();
                }
            }
        }
        if (token != null) {
            return token;
        } else {
            throw new BadCredentialsException("No token");
        }
    }

    private HttpHeaders addTokenToHeaderCookie(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", tokenHeader + "=" +
                token + "; Path=/" + "; Expires=" + jwtTokenUtil.getExpirationDateFromToken(token));
        return headers;
    }

    private Authentication getAuthenticationToken(String email, String password) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password,
                        Collections.singletonList(new SimpleGrantedAuthority(AuthorityName.ROLE_USER.toString())))
        );
        return authentication;
    }

    private Authentication getAuthenticationTokenWithoutVerify(String email) {
        final UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(email, null,
                        Collections.singletonList(new SimpleGrantedAuthority(AuthorityName.ROLE_USER.toString())));
        return authentication;
    }

    private void validateUser(UserDetails userDetails){
        if(!userDetails.isAccountNonLocked()){
            throw new LockedException("Account is deactivated");
        }
    }
}
