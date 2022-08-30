public class Scanner {
    private final BufferedReader in;
    private StringTokenizer s = new StringTokenizer("", " ");
    private String line = "";

    public Scanner(final InputStream stream) {
        in = new BufferedReader(new InputStreamReader(stream));
    }

    private void read() throws IOException {
        line = in.readLine();
        s = new StringTokenizer(line, " ");
    }

    public boolean hasNext() throws IOException {
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

    public String nextLine() throws IOException {
        read();
        return line;
    }

    public int nextInt() throws IOException {
        if (!s.hasMoreTokens()) {
            read();
        }
        return Integer.parseInt(s.nextToken());
    }

    public void close() throws IOException {
        in.close();
    }
}
