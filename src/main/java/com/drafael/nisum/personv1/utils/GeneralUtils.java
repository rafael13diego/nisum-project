package com.drafael.nisum.personv1.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class GeneralUtils {

    public static String getDateInLimaTimeZone() {

        return ZonedDateTime.now(ZoneId.of("America/Lima"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String encodePassword(String password) {
        String passwordEncoded = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digestObject = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(digestObject);
        } catch (NoSuchAlgorithmException e) {
            log.error("Exception catch", e);
        }
        return passwordEncoded;
    }



}