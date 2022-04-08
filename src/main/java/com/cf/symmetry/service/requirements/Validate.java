package com.cf.symmetry.service.requirements;

import com.cf.symmetry.exceptions.ReadRequirementException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Validate {

    public static List<Pair<Character>> getPairs() {

        try {
            Path filePath = Paths.get(ClassLoader.getSystemResource("rules.txt").toURI());
            return Files.readAllLines(filePath).stream()
                    .map(line -> new Pair<>(line.charAt(0), line.charAt(1))).collect(Collectors.toList());

        } catch (NullPointerException | IOException | URISyntaxException e) {
            throw new ReadRequirementException("The file could not be opened!", e);
        }

    }

    public static boolean compareCharacters(char left, char right) {
        return Validate.getPairs().stream()
                .noneMatch(requirement -> requirement.checkPair(left, right));
    }

    public static boolean recognizeChar(char c){
        return Validate.getPairs()
                .stream().anyMatch(pair -> pair.getLeftChar() == c || pair.getRightChar() == c);

    }



}
