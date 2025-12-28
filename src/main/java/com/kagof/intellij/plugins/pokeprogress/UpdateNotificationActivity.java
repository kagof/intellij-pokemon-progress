package com.kagof.intellij.plugins.pokeprogress;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressConfigurable;
import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState;

import icons.PokeIcons;

public class UpdateNotificationActivity implements StartupActivity.DumbAware {
    private static final String PLUGIN_ID = "com.kagof.pokeprogress";
    private static final String NOTIFICATION_GROUP = "Pokemon Progress Update";

    @Override
    public void runActivity(@NotNull final Project project) {
        final IdeaPluginDescriptor descriptor = getPluginDescriptor();
        final PokemonProgressState state = PokemonProgressState.getInstance();
        if (descriptor != null && state != null) {
            final String version = descriptor.getVersion();
            if (!Objects.equals(version, state.version)) {
                state.version = descriptor.getVersion();
                if (state.showUpdateNotification) {
                    sendNotification(project, version);
                }
            }
        }
    }

    @SuppressWarnings("DialogTitleCapitalization")
    private void sendNotification(final Project project, final String version) {
        final Notification n = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP)
            .createNotification("You're now using version "
                + version
                + " of Pok\u00E9mon Progress! \uD83C\uDF89",
                NotificationType.INFORMATION);
        n.setIcon(PokeIcons.SpinningPokeball);
        n.addAction(new DumbAwareAction("Configuration...") {
            @Override
            public void actionPerformed(@NotNull final AnActionEvent e) {
                ShowSettingsUtil.getInstance().showSettingsDialog(project,
                    PokemonProgressConfigurable.class);
            }
        })
            .addAction(new DumbAwareAction("Changenotes") {
                @Override
                public void actionPerformed(@NotNull final AnActionEvent e) {

                    new PokemonProgressChangenotesDialog(project).show();
                }
            })
            .addAction(new DumbAwareAction("Don't show again", "Disable this notification in the future", null) {
                @Override
                public void actionPerformed(@NotNull final AnActionEvent e) {
                    PokemonProgressState.getInstance().showUpdateNotification = false;
                }
            })
            .notify(project);
    }

    public static IdeaPluginDescriptor getPluginDescriptor() {
        return PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID));
    }
}
