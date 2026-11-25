package net.mysticarts.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandLevel;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.mysticarts.monk.MonkData;

public class MysticCommandRegistration {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("mystic")
            .then(CommandManager.literal("setlevel")
                .requires(source -> source.hasPermission(2))
                .then(CommandManager.argument("player", EntityArgument.player())
                    .then(CommandManager.argument("level", IntegerArgumentType.integer(0, 100))
                        .executes(context -> {
                            var player = EntityArgument.getPlayer(context, "player");
                            int level = IntegerArgumentType.getInteger(context, "level");
                            MonkData.setLevel(player, level);
                            context.getSource().sendFeedback(Text.literal("Set " + player.getName().getString() + "'s Mystic Level to " + level), false);
                            return 1;
                        })
                    )
                )
            )
            .then(CommandManager.literal("milestone")
                .requires(source -> source.hasPermission(2))
                .then(CommandManager.argument("player", EntityArgument.player())
                    .then(CommandManager.argument("name", StringArgumentType.word())
                        .executes(context -> {
                            var player = EntityArgument.getPlayer(context, "player");
                            String milestone = StringArgumentType.getString(context, "name");
                            MonkData.addMilestone(player, milestone);
                            context.getSource().sendFeedback(Text.literal("Granted milestone: " + milestone + " to " + player.getName().getString()), false);
                            return 1;
                        })
                    )
                )
            )
        );
    }
}
