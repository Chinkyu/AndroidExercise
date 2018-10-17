// ITimeServiceCallback.aidl
package com.bach.bamtori.aidltest5;

// Declare any non-default types here with import statements

interface ITimeServiceCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    oneway void onTimeChanged( String timeinfo);
}
