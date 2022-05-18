import java.io.*;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WsppPosition {
    public static void main(String[] args) throws IOException {
        Map<String, StringBuilder> words = new LinkedHashMap<>();
        Map<String, Integer> wordsCount = new LinkedHashMap<>();

        Scanner2021 in = new Scanner2021(args[0], "utf8");
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], UTF_8));
        int countLines = 0;
        while (in.hasNextLine()) {
            int countWords = 1;
            countLines++;
            String str = in.nextLine().toLowerCase(Locale.ROOT);
            str += " ";
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'') {
                    word.append(str.charAt(i));
                } else {
                    if (word.length() != 0) {
                        //1 case : if map.contains (word)

                        if (words.containsKey(String.valueOf(word))){
                            int k = wordsCount.get(String.valueOf(word));
                            k++;
                            wordsCount.put(String.valueOf(word), k);
                            words.get(String.valueOf(word)).append(" ").append(countLines).append(":").append(countWords);

                        } else {    // 2 case : else
                            StringBuilder counts = new StringBuilder();
                            counts.append(countLines).append(":").append(countWords);
                            words.put(String.valueOf(word), counts);
                            wordsCount.put(String.valueOf(word), 1);
                        }
                        countWords++;
                    }
                    word = new StringBuilder();
                }
            }
        }

        for (Map.Entry<String, StringBuilder> stringStringBuilderEntry : words.entrySet()) {
            writer.write(stringStringBuilderEntry.getKey() + " " + wordsCount.get(stringStringBuilderEntry.getKey()) + " " + stringStringBuilderEntry.getValue() + '\n');
        }

        writer.close();
        in.close();

    }
}