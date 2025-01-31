package net.dodian.uber.game.model.player.packets.incoming;

import net.dodian.uber.game.model.Position;
import net.dodian.uber.game.model.entity.player.Client;
import net.dodian.uber.game.model.object.GlobalObject;
import net.dodian.uber.game.model.player.packets.Packet;
import net.dodian.uber.game.party.Balloons;

public class ChangeRegion implements Packet {

    @Override
    public void ProcessPacket(Client client, int packetType, int packetSize) {
        if (!client.pLoaded) {
            client.pLoaded = true;
        }
        int wild = client.getWildLevel();
        if (wild > 0) {
            client.setWildLevel(wild);
        } else {
            client.updatePlayerDisplay();
        }
        if (!client.IsPMLoaded) {
            client.refreshFriends();
            client.IsPMLoaded = true;
        }
        client.customObjects();
    }

}
