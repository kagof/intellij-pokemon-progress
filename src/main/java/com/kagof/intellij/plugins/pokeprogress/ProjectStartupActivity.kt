package com.kagof.intellij.plugins.pokeprogress

import com.intellij.ide.plugins.IdeaPluginDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState

/**
 * See https://plugins.jetbrains.com/docs/intellij/plugin-components.html#project-open, must be implemented in Kotlin.
 */
class ProjectStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        PokemonProgressListener.updateProgressBarUi() // ensure the plugin is properly initialized after project open
        sendUpdateNotificationIfRequired(project, PokemonProgressListener.getPluginDescriptor())
    }


    private fun sendUpdateNotificationIfRequired(project: Project, descriptor: IdeaPluginDescriptor?) {
        val state = PokemonProgressState.getInstance()
        if (descriptor != null && state != null) {
            val version = descriptor.version
            if (version != state.version) {
                state.version = descriptor.version
                if (state.showUpdateNotification) {
                    UpdateNotificationSender.sendNotification(project, version)
                }
            }
        }
    }
}