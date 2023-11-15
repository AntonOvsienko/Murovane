package com.sale.ticket.murovane.generator;

import com.sale.ticket.murovane.constants.IdGenerationConstant;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

import static com.sale.ticket.murovane.constants.IdGenerationConstant.CHARACTERS;
import static com.sale.ticket.murovane.constants.IdGenerationConstant.LENGTH;

@Data
@Component
public class RandomStringGenerator {

    public String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
