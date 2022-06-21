package com.general.template.common.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The encoding algorithm used in the old symfony2 backend is replicated in this Password Encoder
 */
public class SymfonySha512PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            return this.encode(rawPassword.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String encode(String rawPassword, String salt) throws Exception {
        return this.encodePassword(rawPassword, salt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            return this.matches(rawPassword.toString(), encodedPassword, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean matches(String rawPassword, String encodedPassword, String salt) throws Exception {
        return this.encode(rawPassword, salt).equals(encodedPassword);
    }

    private String encodePassword(String raw, String salt) throws Exception {
        String salted = this.mergePasswordAndSalt(raw.toString(), salt);
        byte[] digest = DigestUtils.sha512(salted);

        for (int i = 1; i < 5000; i++)
            digest = DigestUtils.sha512(ArrayUtils.addAll(digest, salted.getBytes("UTF-8")));

        return Base64.encodeBase64String(digest);
    }

    private String mergePasswordAndSalt(String password, String salt) throws Exception {
        if (salt == null)
            return password;

        if (-1 != salt.indexOf('{') || -1 != salt.indexOf('}'))
            throw new Exception("Cannot use { or } in salt.");

        return password + "{" + salt + "}";
    }
}