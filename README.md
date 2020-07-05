# Описание приложения
Данное приложение представляет собой копию главной страницы **Instagram** - ленты с постами. Проект написан на языке **Kotlin** с применением архитектурных компонентов - **ViewModel**, **DataBinding** и **LiveData**. Кроме того, в проекте имеются несколько методов покрытых юнит тестами с помощью фреймворка **JUnit**. В качестве источника данных для наполнения постов используется **JSON** объект **Feed** помещенный в файл **"feed.txt"** в папке **assets**. Данный объект имеет одну переменную **"posts"** указывающую на список объектов **"Post"**, информация из которых в свою очередь отображается в постах ленты.
# Пример объекта Post (JSON) :
```
{
    "userPictureUrl":"file:///android_asset/user_picture_1.png",
    "userName":"john_doe",
    "postGeolocation":"Minsk, Belarus",
    "postPhotosUrlsList":[
        "file:///android_asset/post_photo_1.jpg",
        "file:///android_asset/post_photo_2.jpg"
    ],
    "isLiked":true,
    "isSaved":false,
    "likes":{
        "likedByList":[
            "jacob_ai",
            "mia_madison"
        ],
⁣⁣⁣⁣⁣        "othersLiked":96
    },
    "comment":"Фотографии хамелеонов",
    "mentionedList":[
        "@anna_09",
        "@daniel_craig",
        "@greg_prof"
    ],
    "hashTagsList":[
        "#life",
        "#beauty",
        "#nature",
        "#sunny"
    ],
    "postDate":"17 SECONDS AGO"
}
```
# Описание полей объекта Post :
- **[OPTIONAL] userPictureUrl - String** `Путь к файлу из папки "assets" с изображением пользователя.`
- **[REQUIRED] userName - String** `Краткое имя пользователя.`
- **[OPTIONAL] postGeolocation - String** `Геолокация поста.`
- **[REQUIRED] postPhotosUrlsList - List&lt;String>** `Список путей к файлам из папки "assets" с фотографиями поста.`
- **[REQUIRED] isLiked - Boolean** `Статус поста (лайкнут или нет).`
- **[REQUIRED] isSaved - Boolean** `Статус поста (сохранен или нет).`
- **[OPTIONAL] likes - Likes** `Объект содержащий информацию о лайках.`
    - **[OPTIONAL] likedByList - List&lt;String>** `Список последних пользователей лайкнувших пост.`
    - **[OPTIONAL] othersLiked - String** `Количество пользователей которым понравился пост.`
- **[OPTIONAL] comment - String** `Комментарий указанный в посте.`
- **[OPTIONAL] mentionedList - List&lt;String>** `Список имен пользователей упомянутых в посте.`
- **[OPTIONAL] hashTagsList - List&lt;String>** `Список хештегов указанных в посте.`
- **[REQUIRED] postDate - String** `Дата публикации поста.`
