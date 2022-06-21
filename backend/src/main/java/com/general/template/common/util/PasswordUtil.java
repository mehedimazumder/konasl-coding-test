package com.general.template.common.util;

import com.general.template.common.exception.NullPasswordException;
import com.general.template.common.security.SymfonySha512PasswordEncoder;
import com.general.template.common.security.CustomEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    public static int PASS_MIN_SIZE = 6;
    public static final int CRYPTO_STRENGTH = 512;

    public enum EncType {SHA_ENCODER, BCRYPT_ENCODER}

    public static BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static CustomEncoder getPasswordEncoder() {
        return new CustomEncoder();
    }

    public static String encryptPassword(String password) throws Exception {

        if (password == null)
            throw new NullPasswordException("Password can not be empty!");

        return getPasswordEncoder().encode(password);
    }

    public static String encryptPassword(String password, EncType encryptType, String salt) throws Exception {
        if (password == null) throw new NullPasswordException("Password can not be empty!");
        if (encryptType.equals(EncType.SHA_ENCODER)) {
            if (salt != null)
                return getShaPasswordEncoder().encode(password, salt);
            else getShaPasswordEncoder().encode(password);
        } else if (encryptType.equals(EncType.BCRYPT_ENCODER))
            return getBCryptPasswordEncoder().encode(password);
        return getBCryptPasswordEncoder().encode(password);
    }

    public static SymfonySha512PasswordEncoder getShaPasswordEncoder() {
        return new SymfonySha512PasswordEncoder();
    }
}