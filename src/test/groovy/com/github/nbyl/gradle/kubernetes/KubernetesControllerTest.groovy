package com.github.nbyl.gradle.kubernetes

import groovy.mock.interceptor.MockFor
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class KubernetesControllerTest {

    @Test
    void applyManifestCallsKubectl() {
        def manifest = new File('src/test/resources/manifest1.yaml')

        def project = ProjectBuilder.builder().build()
        project.pluginManager.apply('com.github.nbyl.kubernetes')

        def projectMock = new MockFor(Project)
        projectMock.demand.with {
            exec {
                commandLine 'kubectl', 'apply', '-f', "${manifest.absolutePath}"
            }
        }
        def proxyInstance = projectMock.proxyInstance()

        project.metaClass.invokeMethod = {
            String name, Object[] args ->
                if (name == 'exec') {
                    proxyInstance.exec(args.first())
                } else {
                    MetaMethod metaMethod = delegate.class.metaClass.getMetaMethod(name, args)
                    return metaMethod?.invoke(delegate, args)
                }
        }

        def controller = new KubernetesController(project: project)
        controller.applyManifest(manifest)
    }
}
