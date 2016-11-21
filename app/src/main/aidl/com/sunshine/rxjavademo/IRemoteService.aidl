// IRemoteService.aidl
package com.sunshine.rxjavademo;

// Declare any non-default types here with import statements
import com.sunshine.rxjavademo.HelloMsg;
interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,double aDouble, String aString);

    HelloMsg sayHello();
}
