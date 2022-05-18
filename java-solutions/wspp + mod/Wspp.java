import java.io.*;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Wspp{

    public static void main(String[] args) throws IOException {
        Map<String, List<Integer>> wordsCount = new LinkedHashMap<>();

        Scanner2021 in = new Scanner2021(args[0], "utf8");
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], UTF_8));
        int countWords = 1;
      
        while (in.hasNextLine()) {
            String str = in.nextLine().toLowerCase(Locale.ROOT);
            str += " ";
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'') {
                    word.append(str.charAt(i));
                } else {
                    if (word.length() != 0) {

                        if (wordsCount.containsKey(String.valueOf(word))){
                            List<Integer> k = wordsCount.get(String.valueOf(word));
                            k.set(0, k.get(0) + 1);
                            k.add(countWords);
                            wordsCount.put(String.valueOf(word), k);

                        } else {
                            List<Integer> a = new ArrayList<>();
                            a.add(1);
                            a.add(countWords);
                            wordsCount.put(String.valueOf(word), a);
                        }
                        countWords++;
                    }
                    word = new StringBuilder();
                }
            }
        }

        for (Map.Entry<String, List<Integer>> stringStringBuilderEntry : wordsCount.entrySet()) {
            StringBuilder b = new StringBuilder();
            b.append(stringStringBuilderEntry.getKey());
            for (int i = 0; i < stringStringBuilderEntry.getValue().size(); i++) {
                b.append(" ").append(stringStringBuilderEntry.getValue().get(i));
            }
            writer.write(b.toString() + '\n');
        }

        writer.close();
        in.close();
    }
}
