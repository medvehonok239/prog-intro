package markup;

import java.util.List;

public class Strong extends Symbol implements Type {
    public Strong(List<Type> list1){
        super(list1);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        toMarkdown(sb,"__");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        toBBCode(sb,"[b]", "[/b]");
    }
}
