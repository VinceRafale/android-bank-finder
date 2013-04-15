#!/bin/sh

mvn install:install-file \
    -Dfile=$ANDROID_HOME/platforms/android-17/android.jar \
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
    -Dfile=$ANDROID_HOME/extras/android/support/v4/android-support-v4.jar \
    -DgroupId=com.google.android \
    -DartifactId=support-v4 \
    -Dversion=r11 \
    -Dpackaging=jar

mvn install:install-file \
    -Dfile=$ANDROID_HOME/extras/android/support/v4/android-support-v4.jar \
    -DgroupId=com.google.android \
    -DartifactId=support-v4 \
    -Dversion=r12 \
    -Dpackaging=jar