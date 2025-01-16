package com.sd.template.convention

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project

val Project.libs
    get() = project.extensions.getByName("libs") as LibrariesForLibs
