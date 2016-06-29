package com.github.nbyl.gradle.kubernetes

import org.gradle.api.Plugin
import org.gradle.api.Project

class KubernetesPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.add("kubernetes", KubernetesExtension)
    }
}
