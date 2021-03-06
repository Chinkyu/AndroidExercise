// ITimeService.aidl
package com.bach.bamtori.aidltest5;

import com.bach.bamtori.aidltest5.ITimeServiceCallback;

// Declare any non-default types here with import statements

interface ITimeService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    boolean registerTimeServiceCallback( ITimeServiceCallback callback);
    boolean unregisterTimeServiceCallback( ITimeServiceCallback callback);
}
