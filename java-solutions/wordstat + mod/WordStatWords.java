import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 * @created : 10.10.2021
 **/

public class WordStatWords {
    public static void main(String[] args) throws IOException {
    ArrayList<String> words = new ArrayList<>();
    Scanner2021 in = new Scanner2021(args[0],"utf8");
    BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));

    while (in.hasNextLine()) {
        String str = in.nextLine().toLowerCase(Locale.ROOT);
        str += " ";
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c) ||
                Character.getType(c) == Character.DASH_PUNCTUATION || 
                c == '\'') {
                word.append(str.charAt(i));
            } else {
                if (word.length() != 0) {
                    words.add(word.toString());
                }
                word = new StringBuilder();
            }
        }
    }

    Set<String> finAns = new LinkedHashSet<>(words);

    ArrayList<String> finWords = new ArrayList<>(finAns);
    Collections.sort(finWords);
    finAns = new LinkedHashSet<>(finWords);
        for (String finAn : finAns) {
            int count = 0;

            StringBuilder ans = new StringBuilder();
            ans.append(finAn).append(" ");
            for (String word : words) {
                if (Objects.equals(finAn, word)) {
                    count++;
                }
            }

            ans.append(count);
            writer.write(String.valueOf(ans) + '\n');
        }

        writer.close();
        in.close();
    }
}
