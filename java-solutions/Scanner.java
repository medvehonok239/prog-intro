import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 * @created : 30.08.2022, вторник
 **/
public class Scanner {
    private final BufferedReader in;
    private StringTokenizer s = new StringTokenizer("", " ");
    private String line = "";

    public Scanner(final InputStream stream) {
        in = new BufferedReader(new InputStreamReader(stream));
    }

    private void read() {
        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s = new StringTokenizer(line, " ");
    }

    public boolean hasNext() {
        while (true) {
            read();
            if (Objects.equals(line, "") || line == null) {
                return false;
            } else {
                break;
            }
        }
        return s.hasMoreTokens();
    }

    public String nextLine() {
        if (Objects.equals(line, "") || line.length() == 0) {
            read();
        }
        return line;
    }

    public String next() {
        return nextLine();
    }

    public int nextInt() {
        if (!s.hasMoreTokens()) {
            read();
        }
        return Integer.parseInt(s.nextToken());
    }

    public void close() throws IOException {
        in.close();
    }
}
