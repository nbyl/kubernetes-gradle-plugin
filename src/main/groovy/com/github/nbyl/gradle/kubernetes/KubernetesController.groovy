package com.github.nbyl.gradle.kubernetes

class KubernetesController {

    def project

    def applyManifest(manifest) {
        project.exec {
            commandLine 'kubectl', 'apply', '-f', "${manifest}"
        }
    }
}
