package com.cf.symmetry.requirements;

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
public class Requirements {

    public static List<Requirement<Character>> readRequirementPairs() {


        try {
            Path filePath = Paths.get(ClassLoader.getSystemResource("rules.txt").toURI());
            return Files.readAllLines(filePath).stream()
                    .map(line -> new Requirement<>(line.charAt(0), line.charAt(1))).collect(Collectors.toList());

        } catch (NullPointerException | IOException | URISyntaxException e) {
            throw new ReadRequirementException("The file could not be opened!", e);
        }

    }

    public static boolean matchCharactersWithRequirementPair(char left, char right) {
        return Requirements.readRequirementPairs().stream()
                .noneMatch(requirement -> requirement.compareChars(left, right));
    }



}
