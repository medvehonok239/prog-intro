package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 * @created : 21.12.2021
 **/

public class Md2Html {
    public static String replaceHTMLSymb(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '<') {
                sb.append("").append("&lt;").append("");
            } else if (c == '>') {
                sb.append("").append("&gt;").append("");
            } else if (c == '&') {
                sb.append("").append("&amp;").append("");
            } else {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }

    public static String parseInterval(String line) {
        StringBuilder ans = new StringBuilder();
        Map<String, String> map = Map.of("*", "<em>", "**", "<strong>", "_", "<em>", "__", "<strong>", "--", "<s>", "`", "<code>", "%", "<var>");
        line += '\\';
        for (int i = 0; i < line.length() - 1; i++) {
            String testUnary = Character.toString(line.charAt(i));
            String testBin = Character.toString(line.charAt(i)) + line.charAt(i + 1);
            if (map.containsKey(testBin)) {
                if (i > 0 && line.charAt(i - 1) == '\\') {
                    i++;
                    ans.append(testBin);
                    continue;
                }

                boolean hasPair = false;
                
                for (int j = i + 1; j < line.length() - 2; j++) {
                    if (("" + line.charAt(j) + line.charAt(j + 1)).equals(testBin)) {
                        if (line.charAt(j - 1) == '\\') {
                            continue;
                        }
                        String temp = map.get(testBin);
                        ans.append(temp);
                        ans.append(parseInterval(line.substring(i + 2, j)));
                        ans.append(temp.charAt(0)).append("/").append(temp.substring(1));
                        i = j + 1;
                        hasPair = true;
                        break;
                    }
                }
                if (!hasPair) ans.append(testUnary);
            } else if (map.containsKey(testUnary)) {
                if (i > 0 && line.charAt(i - 1) == '\\') {
                    ans.append(testUnary);
                    continue;
                }

                boolean hasPair = false;
                for (int j = i + 1; j < line.length() - 1; j++) {
                    if (Character.toString(line.charAt(j)).equals(testUnary)) {
                        if (line.charAt(j - 1) == '\\' || ( j != line.length() - 1 && line.charAt(j + 1) == line.charAt(j))) {
                            j ++;
                            continue;
                        }
                        
                        String temp = map.get(testUnary);
                        ans.append(temp);
                        ans.append(parseInterval(line.substring(i + 1, j)));
                        ans.append(temp.charAt(0)).append("/").append(temp.substring(1));
                        
                        i = j;
                        hasPair = true;
                        break;
                    }
                }
                if (!hasPair) {
                    ans.append(testUnary);
                }
            } else {
                if (line.charAt(i) != '\\') {
                    ans.append(line.charAt(i));
                }
            }
        }
        
        return ans.toString();
    }

    public static String getPartOfString(String str, int from, int out) {
        if (from == out) {
            return "";
        } else {
            StringBuilder result = new StringBuilder();
            int count = 0;
            for (int j = from; j < out; j++) {
                if (Character.isWhitespace(str.charAt(j))) {
                    count++;
                } else break;
            }

            for (int i = from + count; i < out; i++) {
                result.append(str.charAt(i));

            }
            
            return result.toString();
        }
    }

    public static boolean isHeading(String str) {
        return Character.isWhitespace(str.charAt(levelOfHeading(str))) && (levelOfHeading(str) >= 1);
    }

    public static int levelOfHeading(String str) {
        int count = 0;
        while (str.charAt(count) == '#') {
            count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> map = Map.of("*", "<em>", "**", "<strong>", "_", "<em>", "__", "<strong>", "--", "<s>", "`", "<code>", "%", "<var>");

        List<String> stack = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8));
        BufferedWriter out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));

        StringBuilder ans = new StringBuilder();
        StringBuilder bigSb = new StringBuilder();
        String line = in.readLine();
        StringBuilder mySb = new StringBuilder();
        boolean isFirst = true;
        while (line != null) {
            if (!line.isEmpty()) {
                String test = replaceHTMLSymb(line);
                if (!isFirst) {
                    mySb.append("\n");
                }
                isFirst = false;
                bigSb.append(test).append("\n");
                mySb.append(test);
                line = in.readLine();
            } else {
                isFirst = true;
                String r = parseInterval(mySb.toString());
                ans.append(r);
                System.out.println(r);

                if (bigSb.length() != 0 && isHeading(bigSb.toString())) {
                    int level = levelOfHeading(bigSb.toString());
                    out.write("<h" + level + ">" + getPartOfString(ans.toString(), level + 1, ans.length()) + "</h" + level + ">");
                    out.newLine();
                    ans = new StringBuilder();
                    bigSb = new StringBuilder();
                } else if (bigSb.length() != 0) {
                    out.write("<p>" + ans + "</p>");
                    out.newLine();
                    ans = new StringBuilder();
                    bigSb = new StringBuilder();
                }
                mySb = new StringBuilder();
                line = in.readLine();
            }
        }
        
        String r = parseInterval(mySb.toString());
        ans.append(r);
        System.out.println(r);

        if (bigSb.length() != 0 && isHeading(bigSb.toString())) {
            int level = levelOfHeading(bigSb.toString());
            out.write("<h" + level + ">" + getPartOfString(ans.toString(), level + 1, ans.length()) + "</h" + level + ">");
            out.newLine();
            ans = new StringBuilder();
            bigSb = new StringBuilder();
        } else if (bigSb.length() != 0) {
            out.write("<p>" + ans + "</p>");
            out.newLine();
            ans = new StringBuilder();
            bigSb = new StringBuilder();
        }
        
        out.close();
        in.close();
    }
}
