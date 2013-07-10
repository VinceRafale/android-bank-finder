#!/bin/sh

mvn install:install-file \
    -Dfile=libs/android-17.jar \
    -DgroupId=com.google.android \
    -DartifactId=android \
    -Dversion=17 \
    -Dpackaging=jar
    
mvn install:install-file \
    -Dfile=libs/libGoogleAnalyticsV2.jar \
    -DgroupId=com.google.android \
    -DartifactId=analytics \
    -Dversion=2 \
    -Dpackaging=jar

mvn install:install-file \
    -Dfile=libs/android-support-v4-r12.jar \
    -DgroupId=com.google.android \
    -DartifactId=support-v4 \
    -Dversion=r11 \
    -Dpackaging=jar

mvn install:install-file \
    -Dfile=libs/android-support-v4-r12.jar \
    -DgroupId=com.google.android \
    -DartifactId=support-v4 \
    -Dversion=r12 \
    -Dpackaging=jar

mvn install:install-file \
    -Dfile=libs/android-support-v4-r13.jar \
    -DgroupId=com.google.android \
    -DartifactId=support-v4 \
    -Dversion=r13 \
    -Dpackaging=jar