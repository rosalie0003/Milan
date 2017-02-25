package Server.Communications;
import Server.Communications.*;

/**
 * @author Laurie Dugdale
 */
public class PacketReceiver {

    private Packet clientPacket;

    public PacketReceiver(Packet clientPacket){

        this.clientPacket = clientPacket;
    }

    public Packet getClientPacket(){

        return this.clientPacket;
    }

    public void parsePacket(){

        if(getClientPacket().getType() == 0){

        } else if (getClientPacket().getType() == 1){

        } else if (getClientPacket().getType() == 2) {

        }
    }


}
