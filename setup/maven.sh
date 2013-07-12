#!/bin/sh

mvn install:install-file \
    -Dfile=libs/support-v4-r11.jar \
    -DgroupId=com.google.android \
    -DartifactId=support-v4 \
    -Dversion=r11 \
    -Dpackaging=jar
