import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 * @created : 07.10.2021
 **/

public class ReverseAbc2 {
    public static void main(String[] args) throws IOException {
        Map<Character, Integer> map = Map.of(
                'a', 0,
                'b', 1,
                'c', 2,
                'd', 3,
                'e', 4,
                'f', 5,
                'g', 6,
                'h', 7,
                'i', 8,
                'j', 9 
        );
        
        Scanner2021 in = new Scanner2021(System.in);
        
        ArrayList<Integer> ints = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();

        while (in.hasNextLine()) {
            String str = in.nextLine().toLowerCase(Locale.ROOT);
            str += " ";
            StringBuilder num = new StringBuilder("");
            StringBuilder line = new StringBuilder("");
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isWhitespace(str.charAt(i)) || str.charAt(i) == '-') {
                    num.append(str.charAt(i));
                } else {
                    StringBuilder ans = new StringBuilder("");
                    if (num.length() != 0) {
                        if (num.charAt(0) == '-') {
                            ans.append("-");
                            for (int j = 1; j < num.length(); j++) {
                                ans.append(map.get(num.charAt(j)));
                            }
                        } else {
                            for (int j = 0; j < num.length(); j++) {
                                ans.append(map.get(num.charAt(j)));
                            }
                        }
                        ints.add(Integer.parseInt(String.valueOf(ans)));
                    }
                    num = new StringBuilder("");
                }
            }
            for (int i = ints.size() - 1; i >= 0; i--) {
                line.append(ints.get(i)).append(" ");
            }
            lines.add(String.valueOf(line));
            ints.clear();
        }
        
        in.close();
        
        for (int i = lines.size() - 1; i >= 0; i--) {
            System.out.println(lines.get(i));
        }
    }
}
