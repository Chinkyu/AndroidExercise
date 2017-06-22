//
// Created by bamtori on 17. 1. 24.
//

#include <string.h>
#include <jni.h>
#include "log.h"

extern "C" {


JNIEXPORT jstring JNICALL
Java_com_example_hellojni_HelloJni_mainJNI(JNIEnv *env,
                                           jobject thiz) {

    LOGD("[ckkang] Test Log");
}

}