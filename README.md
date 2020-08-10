# Posts Example

This repo contains an example of a basic implementation of NavigationComponent and PagedList together. 
Also the project is written over Kotlin and follows MVVM pattern

## What's about
* App fetch posts from a dummy service
* App catches posts fetched in sqlLite database.
* App loads catched posts in a PagedListAdapter.
* In the navbar, there are 3 buttons(fetch posts again, list all posts, list favorite posts)
* In the main list app is able to swipe (right to left) and remove the swiped post.
* Every item show a blue dot at left indicating that it's not read yet.
* Every item in list is touchable.
* Every item in list will display more info (detail page) when is touched.
* Once a post is touched and data is displayed, blue dot that indicates item it's not read, will dissapear 
* Every item in list is capable to be marked/unmarked as favorite in the detail page. 

## For improve
* Handle no internet connection.
* Unit testing. 

## Third libraries used

* Paging
* Navigation Component
* Dagger 2
* Retrofit 2
* Room 
* OkHttp

