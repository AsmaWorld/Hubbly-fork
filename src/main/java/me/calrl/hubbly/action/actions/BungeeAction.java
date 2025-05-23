/*
 * This file is part of Hubbly.
 *
 * Hubbly is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Hubbly is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Hubbly. If not, see <http://www.gnu.org/licenses/>.
 */

package me.calrl.hubbly.action.actions;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.calrl.hubbly.Hubbly;
import me.calrl.hubbly.action.Action;
import org.bukkit.entity.Player;

public class BungeeAction implements Action {
    @Override
    public String getIdentifier() {
        return "BUNGEE";
    }

    @Override
    public void execute(Hubbly plugin, Player player, String data) {
        final DebugMode debugMode = plugin.getDebugMode();
        if (plugin.getServer().getMessenger().isOutgoingChannelRegistered(plugin, "BungeeCord")) {
            if(data.contains("server ")) {
                data = data.replace("server ", "");
            }
            final ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(data);
            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
            debugMode.info("Server: " + data);
        } else {
            debugMode.warn("Channel BungeeCord is not registered!");
        }


    }
}
