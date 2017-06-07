node("linux && jdk8") {
    stage "Checkout"
    git url: "https://github.com/roamingthings/junit-rules.git"

    stage "Build/Analyse/Test rules-jpa"
    dir('rules-jpa') {
        sh "../gradlew clean build"
        archiveUnitTestResults()
    }

    stage "Build/Analyse/Test rules-jaxrs"
    dir('rules-jaxrs') {
        sh "../gradlew clean build"
        archiveUnitTestResults()
    }

    stage "Build/Analyse/Test rules-jackson"
    dir('rules-jackson') {
        sh "../gradlew clean build"
        archiveUnitTestResults()
    }

    stage "Build/Analyse/Test rules-cdi"
    dir('rules-cdi') {
        sh "../gradlew clean build"
        archiveUnitTestResults()
    }
}

def archiveUnitTestResults() {
    step([$class: "JUnitResultArchiver", testResults: "build/**/TEST-*.xml"])
}
