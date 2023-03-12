package container.code.function.authentication.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.*;
import container.code.data.dto.*;
import container.code.config.JwtUtils;
import container.code.config.RefreshTokenProvider;
import container.code.data.entity.Account;
import container.code.data.entity.Address;
import container.code.data.repository.AccountRepository;
import container.code.function.authentication.service.AuthService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthServiceImpl implements AuthService {

    private final String companyEmail = "clatt@gmail.com";

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenProvider refreshTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseEntity<ResponseObject> login(LoginForm form) {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
                if (authentication != null) {
                    UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
                    String accessToken = jwtUtils.createToken(userPrinciple);
                    Account account = accountRepository.findByUsername(form.getUsername());
                    AccountDTO accountDTO = mapToAccountDTO(account);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), "Login success!", new JwtResponse(accessToken, accountDTO)));
                } else ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Wrong username or password.", null));
            } catch (Exception e) {
                if (e instanceof DisabledException) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Account has been locked. Please contact " + companyEmail + " for more information", null));
                } else
                if (e instanceof AccountExpiredException){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "The account has expired. Please contact " + companyEmail + " for more information", null));
                } else
                if (e instanceof AuthenticationException){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Wrong username or password.", null));
                } else
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Account is not NULL.", null));

            }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "No username or password.", null));
    }

    public AccountDTO mapToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setRole(account.getRole());
        accountDTO.setAmount(account.getAmount());
        accountDTO.setFullname(account.getFullname());
        accountDTO.setDateOfBirth(account.getDateOfBirth());
        accountDTO.setGender(account.getGender());
        accountDTO.setPhone(account.getPhone());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setBio(account.getBio());
        accountDTO.setProfilePicture(account.getProfilePicture());
        accountDTO.setFcmToken(account.getFcmToken());

        List<Address> addresses = account.getAddresses();
        List<String> locations = new ArrayList<>();

        if (addresses.size() == 0) {
            for (Address address : addresses) {
                locations.add(address.getDescription() + ", " + address.getDistrict().getName() + ", " + address.getDistrict().getProvince().getName());
            }

            accountDTO.setLocations(locations);
        }

        return accountDTO;
    }

    @Override
    public ResponseEntity<ResponseObject> loginWithIdToken(String token, String role) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TokenRequest tokenRequest = mapper.readValue(token, TokenRequest.class);
            String tokenValue = tokenRequest.getAccessToken();
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(tokenValue);
            String email = decodedToken.getEmail();
            Account account = accountRepository.findByEmail(email);
            if (account == null) {
                String username = RandomStringUtils.randomAlphanumeric(10);
                while (accountRepository.findByUsername(username) != null) username = RandomStringUtils.randomAlphanumeric(10);
                String password = passwordEncoder.encode(RandomStringUtils.randomAlphanumeric(10));
                Account addAccount = new Account();
                addAccount.setUsername(username);
                addAccount.setPassword(password);
                addAccount.setEmail(email);
                switch (role) {
                    case "employee":
                        addAccount.setRole("employee");
                        break;
                    default:
                        addAccount.setRole("renter");
                }
                accountRepository.save(addAccount);
                account = addAccount;
            }
            if (account != null) {
                UserPrinciple userPrinciple = UserPrinciple.build(account);
                String accessToken = jwtUtils.createToken(userPrinciple);
                AccountDTO accountDTO = mapToAccountDTO(account);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), "Login success!", new JwtResponse(accessToken, accountDTO)));
            } else ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Wrong username or password.", null));
        } catch (Exception e) {
            if (e instanceof DisabledException) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Account has been locked. Please contact " + companyEmail + " for more information", null));
            } else
            if (e instanceof AccountExpiredException){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "The account has expired. Please contact " + companyEmail + " for more information", null));
            } else
            if (e instanceof AuthenticationException){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Wrong username or password.", null));
            } else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Account is not NULL.", null));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Something wrong occur.", null));
    }
    public boolean validateEmail(String email) {
        //Regular Expression
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        //Create instance of matcher
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public ResponseEntity<ResponseObject> createAccount(CreateAccountForm form) {
        if (accountRepository.findByUsername(form.getUsername()) == null)
        try {
            Account account = new Account();
            account.setUsername(form.getUsername());
            account.setPassword(passwordEncoder.encode(form.getPassword()));
            form.setRole(form.getRole().trim());
            if (form.getRole().equals("") || form.getRole() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "There is no role.", null));
            } else {
                if (!form.getRole().equals("employee")) form.setRole("renter");
                account.setRole(form.getRole());
                accountRepository.save(account);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), "Create account successfully.", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur.", null));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Username is existed.", null));
    }

    @Override
    public ResponseEntity<ResponseObject> sendIdTokenVerify(String token) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TokenRequest tokenRequest = mapper.readValue(token, TokenRequest.class);
            String tokenValue = tokenRequest.getAccessToken();
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(tokenValue);
            String email = decodedToken.getEmail();
            Account account = accountRepository.findByEmail(email);
            if (account != null) return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), "No account in database! Need to create.", null));
            else {
                UserPrinciple userPrinciple = UserPrinciple.build(account);
                String accessToken = jwtUtils.createToken(userPrinciple);
                AccountDTO accountDTO = mapToAccountDTO(account);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), "Login success!", new JwtResponse(accessToken, accountDTO)));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur.", null));
        }
    }
}
