package com.kagof.intellij.plugins.pokeprogress;

import org.jetbrains.annotations.NotNull;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressConfigurable;
import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState;

import icons.PokeIcons;

public final class UpdateNotificationSender {
    private static final String NOTIFICATION_GROUP = "Pokemon Progress Update";

    @SuppressWarnings("DialogTitleCapitalization")
    public static void sendNotification(final Project project, final String version) {
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
}
