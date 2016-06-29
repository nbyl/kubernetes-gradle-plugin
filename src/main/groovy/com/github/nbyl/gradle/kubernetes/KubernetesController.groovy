package com.github.nbyl.gradle.kubernetes

class KubernetesController {

    def project

    def applyManifest(manifest) {
        runKubectl('apply', '-f', "${manifest}")
    }

    private def runKubectl(Object... args) {
        def commandLineArgs = ['kubectl']

        if (project.kubernetes && project.kubernetes.configFile) {
            commandLineArgs << "--kubeconfig=${project.kubernetes.configFile.absolutePath}"
        }
        commandLineArgs.addAll(args)

        project.exec {
            commandLine commandLineArgs
        }
    }
}
