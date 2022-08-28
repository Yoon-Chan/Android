// MyAIDLInterface.aidl
package com.example.ch15_service;

// Declare any non-default types here with import statements

interface MyAIDLInterface {
    int getMaxDuration();
    void start();
    void stop();
}