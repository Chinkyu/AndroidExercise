//
// Created by bamtori on 18. 10. 11.
//
#include <jni.h>
#include <string>
#include "log.h"

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_bamtori_nativetest2_NativeTest2_mainFromJNI(
        JNIEnv *env,
        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
    LOGD("[ckkang] logging in mainFromJNI");

    return env->NewStringUTF("test");
}