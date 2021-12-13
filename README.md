# nab_development_challenge

## App Overview

* Programming Language: Kotlin
* Design Pattern: Single Activity + MVVM Design Pattern (using LiveData)
* Dependance Injection: Dagger 2
* For Concurrency Execution: Kotlin Coroutine
* For Navigation: Navigation Graph + Safe Args (Android JetPack)
* For Repository:
	* Network: Retrofit + Gson
	* Local: Room Database
	* SharePreference: DataStore( Android JetPack)
* For Security: I use android keystore to retrive master key. This key is used in DataStore and SqlCipher (security library for Room Database)

![Alt text](https://github.com/khoantt91/nab_development_challenge/blob/master/app_overview.png)

## App Folder Package Structure
* "common" package: store common base class, such as: BaseActivity, BaseFragment, BaseViewModel, BaseRecyclerAdapter, ApplicationGlideModule and GsonTypeAdapter.
* "model" package: all of application models
* "repository" package: contain data business logic. There are 3 package in repository: datastore, local and network. Each package will have single reposibility for retriving or inserting data and data in repository will not be retrived directly but it will through a wapper class like RepositoryDataSource.
	* datastore: has reposibility for data of SharePreference
	* local: has reposibility for data of Local Database (SqlLite)
	* network: has reposibility for data of Network Api Service
* "utils" package: contain common helper class, helper methods or extension etc...
* "view" package: contain all of feature view like activity, fragment or custom view in app etc...

## Items Has Been Done Checklist
1. Programming language: Kotlin (DONE)
2. Design app'sarchitecture (suggest MVVM) (DONE)
3. Apply LiveData mechanism (DONE)
4. UI should be looks like in attachment (DONE)
5. Write Unit Tests (DONE)
6. Acceptance Tests
7. Exception handling (DONE)
8. Caching handling (DONE)
9. Secure Android (DONE)
10. Accessibility for Disability Supports (DONE)
11. Readme file includes (DONE)

#### Clean

	gradle clean
