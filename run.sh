#!/usr/bin/zsh
./gradlew :createFatJar

jamvm -version
jamvm -Xms64m -Xmx128m -Dsun.java2d.uiScale=1.0 -jar build/libs/testcase_gnu_classpath.jar
