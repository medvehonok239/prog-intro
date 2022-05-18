package markup;

public interface Type {

    void toMarkdown(StringBuilder sb);

    void toBBCode(StringBuilder sb);
}
