# nab_development_challenge

## Structure

* For Design Pattern: Single Activity + MVVM Design Pattern
* For Dependance Injection: Dagger 2
* For Concurrency Execution: Coroutine
* For Navigation: Navigation (Android JetPack)
* For Repository:
	* Network: Retrofit + Gson
	* Local: Room Database
	* SharePreference: DataStore( Android JetPack)
* For Security: I use android keystore to retrive master key. This key is used in DataStore and SqlCipher (security library for Room Database)

![Alt text](https://github.com/khoantt91/android-template-source/blob/feature/mvvm_dagger_realm_couroutine_template/Structure.png)

#### Clean

	gradle clean

#### Test

Should use instrusment test for local database using Realm

#### Note

Base on format reponse from server, you should define the way to handle response in NetworkCore Class
