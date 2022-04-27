package com.cf.symmetry.service.requirements;

import com.cf.symmetry.exceptions.ReadRequirementException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class Reader {

  public List<Pair<Character>> readPairs() {

    try {
      Path filePath = Paths.get(ClassLoader.getSystemResource("rules.txt").toURI());
      return Files.readAllLines(filePath).stream()
          .map(line -> new Pair<>(line.charAt(0), line.charAt(1))).toList();

    } catch (NullPointerException | IOException | URISyntaxException e) {
      throw new ReadRequirementException("The file could not be opened!", e);
    }

  }

  public boolean compareCharacters(char left, char right) {
    return readPairs().stream()
        .noneMatch(pair -> pair.checkPair(left, right));
  }

  public boolean recognizeChar(char c) {
    return readPairs()
        .stream().anyMatch(pair -> pair.getLeftChar() == c || pair.getRightChar() == c);

  }


}
