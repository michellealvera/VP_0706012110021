// Generated by Dagger (https://dagger.dev).
package com.vp.week4retrofit.retrofit;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_GetRetrofitInstanceFactory implements Factory<Retrofit> {
  @Override
  public Retrofit get() {
    return getRetrofitInstance();
  }

  public static AppModule_GetRetrofitInstanceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Retrofit getRetrofitInstance() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.getRetrofitInstance());
  }

  private static final class InstanceHolder {
    private static final AppModule_GetRetrofitInstanceFactory INSTANCE = new AppModule_GetRetrofitInstanceFactory();
  }
}
