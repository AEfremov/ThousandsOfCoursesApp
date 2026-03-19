pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ThousandsOfCoursesApp"
include(":app")
include(":core:core-di")
include(":core:core-remote")
include(":core:core-ui")
include(":core:core-domain")
include(":core:core-data")
include(":feature:feature-auth")
include(":feature:feature-courses")
