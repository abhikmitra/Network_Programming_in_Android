# Network Programming in Android
This is a set of examples that I wrote for demonstrating the various way to do Http Calls in Android.It has 4 projects each containing an app.
The objectove of all the application is to fetch a JSON(cuisine.json) 10 times and check how much time it takes to do so. The TimeKeeper class in every file does that.
- [MyApplication] - Demo shows how to fetch JSON using HTTPUrlConnection
- [okHttpDemo] - Demo shows how to fetch JSON uing okHTTP
- [volleyOk] - Demo shows how to integrate Volley with OkHttp 
- [RetrofitDemo] - Demo shows how to fetch JSON with Retrofit
- [Fresco] - Demo fetches 3 images. One ach button click it fetches a new image from a total of 3. Subsequesnt images load faster because of cache

okHttpDemo also includes stetho to show how we can inspect network calls in Browsers. The accompanying slide deck can be found [here](http://slides.com/abhikmitra/http-networking-in-android#/)
