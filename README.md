# nab_development_challenge

## Structure

* Single Activity with MVVM Design Pattern + Dagger 2 + Coroutine + Navigation (Android Jetpack)
* Repository: 
      - Network: Retrofit + Gson
      - Local: Room Database
      - SharePreference: DataStore

![Alt text](https://github.com/khoantt91/android-template-source/blob/feature/mvvm_dagger_realm_couroutine_template/Structure.png)

#### Clean

	gradle clean

#### Test

Should use instrusment test for local database using Realm

#### Note

Base on format reponse from server, you should define the way to handle response in NetworkCore Class
