package Server.Communications;

public class Packet {

    private int type;
    private Memo memo;

    public Packet(Memo memo) {
        this.memo = memo;

        if(memo.getClass() == Message.class) {
            this.type = 0;
        }
        else if(memo.getClass() == History.class) {
            this.type = 1;
        }
        else if(memo.getClass() == HistoryRequest.class) {
            this.type = 2;
        }
        else {
            System.out.println("Could not write packet.");
            // throw new UnknownPacketException(); ?????????????
            this.type = -1;
        }
    }

    public int getType() {
        return type;
    }

    public Memo getMemo() {
        return memo;
    }

}
