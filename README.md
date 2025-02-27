# Multiavatar #

[![badge-latest-release]][url-latest-release]
[![badge-license]][url-license]
[![badge-kotlin]][url-kotlin]

![badge-platform-android]
![badge-platform-ios]
![badge-platform-wasm]
![badge-platform-js]
![badge-support-js-ir]
![badge-platform-jvm]
![badge-platform-linux]
![badge-platform-macos]
![badge-platform-windows]

<img src="https://raw.githubusercontent.com/multiavatar/Multiavatar/main/logo.png?v=001" width="65">

This is a Kotlin transcription of the original [Multiavatar script](https://github.com/multiavatar/Multiavatar) compatible with Compose Multiplatform.

[Multiavatar](https://multiavatar.com) is a multicultural avatar maker.

Multiavatar represents people from multiple races, multiple cultures, multiple age groups, multiple worldviews and walks of life.

In total, it is possible to generate **12,230,590,464** unique avatars.


### Installation and usage ###

Using Gradle:

```kotlin
    implementation("io.github.mew22:multiavatar:<version>")
```

API:
```kotlin
    // Create manually
    val avatarData = AvatarData(
        background = AvatarDataPart.getRandomPart(),
        clothes = AvatarDataPart.getRandomPart().increment(),
        head = AvatarDataPart.getOrThrow(1),
        mouth = AvatarDataPart.getRandomPart().decrement(),
        eyes = AvatarDataPart.getRandomPart(),
        top = AvatarDataPart.getRandomPart(),
    )

    // Create from string formatted following this pattern: "<int>|<int>|<int>|<int>|<int>|<int>" 
    // with <int> an integer between 0 and 47 following this order: background, clothes, head, mouth, eyes, top
    val avatarData = AvatarData.generateFromFormattedString(iconString).getOrThrow()
    
    // Create from string by hashing it with SHA-256 (safe)
    val avatarData = AvatarData.generateWithSha256(iconString).getOrThrow()
    
    // From random
    val avatarData = AvatarData.generateRandom()
    
    // Get Svg string
    val svgString = MultiAvatar.getAvatarSvgString(avatarData)
    
    // Get Byte array
    val svgBytes = MultiAvatar.getAvatarSvgBytes(avatarData)
```

With Coil3 SVG support:
```kotlin
    val playerIconBytes = remember(iconString) {
        val avatarData = AvatarData.generateFromFormattedString(iconString).getOrThrow()
        MultiAvatar.getAvatarSvgBytes(avatarData) 
    }
    AsyncImage(
        model = ImageRequest.Builder(context = LocalPlatformContext.current)
            .data(playerIconBytes)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(184.dp)
            .border(
                2.dp,
                Color(0xffD9D9D9),
                CircleShape
            )
            .clip(CircleShape)
            .clickable { },
        error = ColorPainter(Color(0xffBC1A3F)),
    )
```


### Info ###

The initial unique 48 (16x3) avatar characters are designed to work as the source from which all 12 billion avatars are generated.

Every avatar consists of 6 parts:
- Environment
- Clothes
- Head
- Mouth
- Eyes
- Top

Also, there are different versions of different parts. In some final avatars, additional parts are hidden with transparency (`none` for the color) to create different shapes by re-using the same code. Also, each avatar has 3 unique color themes that are defined in the script. In total, there are: `16 characters x 3 versions/themes = 48 initial unique avatars`. You can see them all by opening the `svg/index.html` file in your browser.

To create new avatars, the Multiavatar script mixes different parts of different avatars, and different color themes.

The total number of unique avatars: 48^6 = 12,230,590,464

One of the main Multiavatar functions is to work as an identicon. Every unique avatar can be identified by the unique string of characters, associated with the avatar.

The string of characters is also the input for the Multiavatar script, which converts the provided string into a 6 double-digit numbers (range 00-47), each representing an individual part of the final avatar.

`000000000000` - this string of numbers represents the very first avatar + its A theme. You can also read it like this: `00 00 00 00 00 00`.

`474747474747` - this is the 12,230,590,464th avatar (or the 16th initial avatar + its "C" color theme).

### Web API (SHA256) ###

To get an avatar as SVG code, add the avatar's ID to the URL:

```
https://api.multiavatar.com/Binx Bond
```

To get an avatar as SVG file, add .svg to the end of the URL:

```
https://api.multiavatar.com/Binx Bond.svg
```
To get an avatar as PNG file, add .png to the end of the URL:

```
https://api.multiavatar.com/Binx Bond.png
```


### License ###

You can use Multiavatar for free, as long as the conditions described in the [LICENSE](LICENSE) are followed.


### Screenshots ###

<img src="https://github.com/mew22/Multiavatar/blob/main/screenshots/screenshot.gif"/>


### More info ###

For additional information and extended functionality, visit the [multiavatar.com](https://multiavatar.com) web-app.


<!-- TAG_VERSION -->
[badge-latest-release]: https://img.shields.io/badge/latest--release-1.0.1-blue.svg?style=flat
[badge-license]: https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat

<!-- TAG_DEPENDENCIES -->
[badge-kotlin]: https://img.shields.io/badge/kotlin-2.1.0-blue.svg?logo=kotlin

<!-- TAG_PLATFORMS -->
[badge-platform-android]: http://img.shields.io/badge/-android-6EDB8D.svg?style=flat
[badge-platform-jvm]: http://img.shields.io/badge/-jvm-DB413D.svg?style=flat
[badge-platform-js]: http://img.shields.io/badge/-js-F8DB5D.svg?style=flat
[badge-platform-linux]: http://img.shields.io/badge/-linux-2D3F6C.svg?style=flat
[badge-platform-macos]: http://img.shields.io/badge/-macos-111111.svg?style=flat
[badge-platform-ios]: http://img.shields.io/badge/-ios-CDCDCD.svg?style=flat
[badge-platform-wasm]: https://img.shields.io/badge/-wasm-624FE8.svg?style=flat
[badge-platform-windows]: http://img.shields.io/badge/-windows-4D76CD.svg?style=flat
[badge-support-js-ir]: https://img.shields.io/badge/support-[js--IR]-AAC4E0.svg?style=flat

[url-kotlin]: https://kotlinlang.org
[url-latest-release]: https://github.com/mew22/multiavatar/releases/latest
[url-license]: https://www.apache.org/licenses/LICENSE-2.0.txt