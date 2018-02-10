package ua.lviv.courierdelivery.security.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.courierdelivery.model.enums.AuthorityName;
import ua.lviv.courierdelivery.security.DTO.JwtAuthenticationRequest;
import ua.lviv.courierdelivery.security.DTO.JwtAuthenticationResponse;
import ua.lviv.courierdelivery.security.JwtTokenUtil;
import ua.lviv.courierdelivery.security.JwtUser;
import ua.lviv.courierdelivery.security.service.AuthenticationRestService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

@RestController
public class AuthenticationRestController {

//    @Value("${app.jwt.header}")
//    private String tokenHeader;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Autowired
//    private UserDetailsService userDetailsService;

//    @RequestMapping(value = "${app.jwt.route.authentication.path}", method = RequestMethod.POST)
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
//
//        // Perform the security
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authenticationRequest.getUsername(),
//                        authenticationRequest.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Reload password post-security so we can generate token
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        final String token = jwtTokenUtil.generateToken(userDetails, device);
//
//        // Return the token
//        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
//    }

//    @RequestMapping(value = "${app.jwt.route.authentication.refresh}", method = RequestMethod.GET)
//    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
//        String authToken = request.getHeader(tokenHeader);
//        final String token = authToken.substring(7);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
//
//        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
//            String refreshedToken = jwtTokenUtil.refreshToken(token);
//            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
//        } else {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

    @Autowired
    private AuthenticationRestService authenticationRestService;

    @RequestMapping(value = "${app.jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, Device device) {
        HttpHeaders headers = authenticationRestService.getAuthHeaders(authenticationRequest.getUsername(),
                authenticationRequest.getPassword(), device);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @RequestMapping(value = "${app.jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity refreshAndGetAuthenticationToken(HttpServletRequest request) {
        HttpHeaders headers = authenticationRestService.getHeadersForRefreshToken(request);
        return new ResponseEntity<>(new JwtAuthenticationResponse("OK"), headers, HttpStatus.OK);
    }
}
