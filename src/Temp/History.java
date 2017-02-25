package Server.Communications;
import java.util.List;

public class History extends Memo {

    private List<String> history;

    public History(int chatID, int appTarget, List<String> history) {
        super(chatID, appTarget);
        this.history = history;
    }

    public List<String> getHistory() {
        return history;
    }
}
