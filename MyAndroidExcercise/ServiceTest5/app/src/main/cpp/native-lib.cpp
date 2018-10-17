#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_bach_bamtori_servicetest5_ServiceTest5MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
