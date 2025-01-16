plugins {
    alias(libs.plugins.com.sd.template.kmp.app.plugin)
    alias(libs.plugins.com.sd.template.cmp.plugin)
}

tasks.register("runDebug", Exec::class) {
    dependsOn("clean", "uninstallDebug", "installDebug")
    commandLine(
        android.adbExecutable.path, "shell", "am", "start", "-n",
        "com.sd.template/com.sd.template.MainActivity"
    )
}
