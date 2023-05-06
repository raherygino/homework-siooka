# homework-siooka
Application to make merchants easily communicate with their customers
### Objective
- Get current user’s location (and prefill the text box) or
allow the user to enter
- Ask for a Keyword (which will be the shop category in
this case. For ex. “bakery”, “coffee”, “breakfast”...)
- Use this location and the keyword to ask to the
Foursquare’s API to search the first 10 results (a shop
is called a “venue” in the API)
- Display the results and let the user choose which one
corresponds to his shop.
- After the selection, we’ll retrieve the details of the
corresponding Foursquare’s venue, to get its stats, its
ranking and display it to the user, for this demo
purpose you can present this info in a small popup.

### Preview
<table>
    <tr>
        <td><img src="https://raw.githubusercontent.com/raherygino/homework-siooka/main/app/screenshots/01.jpg" width="200" /></td>
        <td><img src="https://raw.githubusercontent.com/raherygino/homework-siooka/main/app/screenshots/02.jpg" width="200" /></td>
        <td><img src="https://raw.githubusercontent.com/raherygino/homework-siooka/main/app/screenshots/03.jpg" width="200" /></td>
        <td><img src="https://raw.githubusercontent.com/raherygino/homework-siooka/main/app/screenshots/04.jpg" width="200" /></td>
        <td><img src="https://raw.githubusercontent.com/raherygino/homework-siooka/main/app/screenshots/05.jpg" width="200" /></td>
    </tr>
</table>

### Installation
- git clone https://github.com/raherygino/homework-siooka.git
- synchonize <code>app/build.gradle</code> for installation all library
```java
dependencies {

    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.8.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.navigation:navigation-fragment:2.5.3'
    implementation 'androidx.navigation:navigation-ui:2.5.3'

    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.2.0'

    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}
```
- :zap: Run
