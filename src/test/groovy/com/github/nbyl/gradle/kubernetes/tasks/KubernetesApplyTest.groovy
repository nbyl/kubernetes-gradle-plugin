package com.github.nbyl.gradle.kubernetes.tasks

import com.github.nbyl.gradle.kubernetes.KubernetesController
import groovy.mock.interceptor.StubFor
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static junit.framework.TestCase.assertTrue

class KubernetesApplyTest {

    @Test
    void callsApplyManifestForEveryManifest() {
        Project project = ProjectBuilder.builder().build();
        def task = project.task(type: KubernetesApply, "deploy", {
            manifestDirs(new File('src/test/resources').absolutePath)
        })
        assertTrue(task instanceof KubernetesApply)

        def controllerMock = new StubFor(KubernetesController)
        controllerMock.demand.applyManifest { new File('src/test/resources/subdir/manifest2.yml') }
        controllerMock.demand.applyManifest { new File('src/test/resources/manifest1.yml') }

        controllerMock.use {
            task.controller = new KubernetesController()
            task.deploy()

        }

        controllerMock.expect.verify()
    }
}
