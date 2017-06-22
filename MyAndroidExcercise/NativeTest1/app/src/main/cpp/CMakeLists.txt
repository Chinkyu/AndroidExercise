cmake_minimum_required(VERSION 3.4.1)

add_library(hello-jni SHARED
            hello-jni.c)

add_library(main-jni SHARED
            main-jni.cpp
            NQueenBT.cpp)

# Include libraries needed for hello-jni lib
target_link_libraries(hello-jni
                      android
                      log)

target_link_libraries(main-jni
                      android
                      log)
