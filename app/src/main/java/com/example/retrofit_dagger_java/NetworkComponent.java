package com.example.retrofit_dagger_java;

import com.example.retrofit_dagger_java.dependency.NetworkModule;

import dagger.Component;

@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    void inject(MovieDataSource dataSource);
}
