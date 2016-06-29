package com.github.nbyl.gradle.kubernetes.tasks

import com.github.nbyl.gradle.kubernetes.KubernetesController
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class KubernetesApply extends DefaultTask {

    def KubernetesController controller

    def manifestDirs = ['src/main/kubernetes']
    def manifestInclude = '**/*.yaml'

    KubernetesApply() {
        controller = new KubernetesController(project: project)
    }

    def deployFile(def manifest) {
        project.exec {
            commandLine 'kubectl', 'apply', '-f', "${manifest}"
        }
    }

    @TaskAction
    def deploy() {
        project.files(manifestDirs).each {
            manifestDir ->
                project.fileTree(dir: manifestDir, include: manifestInclude).each {
                    controller.applyManifest(it)
                }
        }
    }

}