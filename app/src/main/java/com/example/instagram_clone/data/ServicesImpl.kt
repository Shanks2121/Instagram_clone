package com.example.instagram_clone.data

import com.example.instagram_clone.R
import com.example.instagram_clone.UserSession
import com.example.instagram_clone.models.Chat
import com.example.instagram_clone.models.Featured
import com.example.instagram_clone.models.Message
//import com.example.instagram_clone.models.MyNotification
import com.example.instagram_clone.models.Post
import com.example.instagram_clone.models.PostReacts
import com.example.instagram_clone.models.Story
import com.example.instagram_clone.models.User
import com.example.instagram_clone.sealed.DataResponse
import com.example.instagram_clone.sealed.Error
//import com.example.instagram_clone.sealed.NotificationType
import kotlinx.coroutines.delay
import java.util.*
import kotlin.random.Random

class ServicesImpl : Services {
    private val users = listOf(
        User(
            userId = 1,
            userName = "PhilippAndroid",
            name = "Philipp Lackner",
            profile = R.drawable.mentor_yes,
        ),
        User(
            userId = 2,
            userName = "Charlie Puth",
            name = "Charlie Puth",
            profile = R.drawable.charlie_profile,
        ),
        User(
            userId = 3,
            userName = "elon_musk",
            name = "Elon Musk",
            profile = R.drawable.elon_profile,
        ),
        User(
            userId = 4,
            userName = "the_rock",
            name = "Dwayne Johnson",
            profile = R.drawable.rock_profile,
        ),
    )
    private val posts = mutableListOf<Post>()
    private val stories = mutableListOf<Story>()
    private val images = listOf(
        R.drawable.diamond_tower,
        R.drawable.tokyo_tower,
        R.drawable.tajmahal_night,
        R.drawable.android_dev,
        R.drawable.mentor_yes,
        R.drawable.space_space,
        R.drawable.mountain_ha,
        R.drawable.mountain_ha,
        R.drawable.ocean_waves,
        R.drawable.space_space,
        R.drawable.wine_cupsss,
    )

    init {

        images.forEachIndexed { index, image ->
            posts.add(
                Post(
                    id = index,
                    user = users.random(),
                    location = listOf(
                        "China, Beijing",
                        "CA, USA",
                        "India, Delhi",
                        "Madrid, Spain",
                        "london, U.K",
                    ).random(),
                    images = images.shuffled().take((Random.nextInt(1, 5))),
                    caption = listOf(
                        "what's going on folks",
                        "DEV",
                        "I see you",
                        "Change is a constant",
                        "mai yahi hu"
                    ).random(),
                    reacts = PostReacts(
                        recentUser = User(
                            userId = 5,
                            userName = "charlie",
                            profile = R.drawable.charlie_profile,
                        ),
                        othersCount = (Math.random() * 200000).toInt(),
                    ),
                )
            )
        }

        stories.addAll(
            users.map {
                Story(
                    id = it.userId,
                    url = images[Random.nextInt(0, images.size)],
                    user = it
                )
            }
        )
    }

    override suspend fun signInUser(email: String, password: String): DataResponse<User> {
        /** Faking the process of authenticating actual user, wait for 3 seconds and then go */
        delay(3000)
        return DataResponse.Success(
            data = User(
                userId = 1,
                name = "Shanks",
                userName = "Shanks",
                email = email,
                profile = R.drawable.profile,
                token = " ",
                followers = users,
                following = users,
            ).also { user ->
                user.posts = posts.map { post -> post.copy(user = user) }
            }
        )
    }



    override suspend fun getFakePosts(): DataResponse<List<Post>> {
        return DataResponse.Success(data = posts)
    }



    override suspend fun getFakeStories(): DataResponse<List<Story>> {
        delay(4000)
        return DataResponse.Success(data = stories)
    }

    override suspend fun getFakeFeaturedStories(): DataResponse<List<Featured>> {
        return UserSession.user?.let {
            DataResponse.Success(
                data = images.mapIndexed { index, drawable ->
                    Featured(
                        id = index,
                        user = it,
                        title = "Feat $index",
                        images = listOf(drawable),
                    )
                }
            )
        } ?: DataResponse.Error(error = Error.Network)
    }

    override suspend fun findStoryById(storyId: Int): DataResponse<Story?> {
        return DataResponse.Success(data = stories.find { it.id == storyId })
    }

    override suspend fun findPostById(postId: Int): DataResponse<Post?> {
        return DataResponse.Success(data = posts.find { it.id == postId })
    }

    override suspend fun getFakeUsers(userName: String): DataResponse<List<User>> {
        return DataResponse.Success(data = users)
    }

    override suspend fun findUserByUsername(userName: String): DataResponse<User?> {
        return DataResponse.Success(data = users.find { it.userName == userName })
    }
}