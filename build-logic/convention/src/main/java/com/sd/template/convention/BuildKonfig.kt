package com.sd.template.convention

import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import org.gradle.api.Project
import com.codingfeline.buildkonfig.compiler.FieldSpec

internal fun Project.configureBuildKonfig(
    extension: BuildKonfigExtension,
) = extension.apply {
    packageName = "com.sd.template"

    // default config is required
    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "flavor", "")
    }

    defaultConfigs("mock") {
        buildConfigField(FieldSpec.Type.STRING, "flavor", "mock")
    }

    defaultConfigs("dev") {
        buildConfigField(FieldSpec.Type.STRING, "flavor", "dev")
    }

    defaultConfigs("staging") {
        buildConfigField(FieldSpec.Type.STRING, "flavor", "staging")
    }

    defaultConfigs("prod") {
        buildConfigField(FieldSpec.Type.STRING, "flavor", "prod")
    }
}