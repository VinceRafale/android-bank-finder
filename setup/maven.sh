#!/bin/sh

mvn install:install-file \
    -Dfile=libs/support-v4-r11.jar \
    -DgroupId=com.google.android \
    -DartifactId=support-v4 \
    -Dversion=r11 \
    -Dpackaging=jar

mvn install:install-file \
    -Dfile=libs/support-v4-r13.jar \
    -DgroupId=com.google.android \
    -DartifactId=support-v4 \
    -Dversion=r13 \
    -Dpackaging=jar

mvn install:install-file \
    -Dfile=libs/analytics-2.0.jar \
    -DgroupId=com.google.android \
    -DartifactId=analytics \
    -Dversion=2.0 \
    -Dpackaging=jar