package com.route.composefacebookposts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.route.composefacebookposts.ui.theme.ComposeFacebookPostsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFacebookPostsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PostsList()

                }
            }
        }
    }
}

@Composable
fun ItemPost(post: Post) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (profImage, username, timeText, lang,
            option, postText, postImage, likeCount, shareCount,
            like, comment, share) = createRefs()


        Image(   // profile image
            painter = painterResource(id = R.drawable.spongebob),
            contentDescription = "profilePicture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .constrainAs(profImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text(  // username
            text = "SpongeBob",
            color = Color.Black,
            modifier = Modifier
                .constrainAs(username) {
                    start.linkTo(profImage.end, margin = 16.dp)
                    top.linkTo(profImage.top, margin = 16.dp)
                }
        )
        Text(  //time
            text = stringResource(id = post.timeId),
            color = Color.DarkGray,
            modifier = Modifier.constrainAs(timeText) {
                top.linkTo(username.bottom, margin = 8.dp)
                start.linkTo(username.start)
            }
        )

        Icon(painter = painterResource(id = R.drawable.ic_earth), contentDescription = "language",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(16.dp)
                .constrainAs(lang) {
                    top.linkTo(timeText.top)
                    start.linkTo(timeText.end, margin = 8.dp)
                    bottom.linkTo(timeText.bottom)
                }

        )
        Icon(painter = painterResource(id = R.drawable.ic_options), contentDescription = "option",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(option) {
                    end.linkTo(parent.end)
                    top.linkTo(username.top)
                    bottom.linkTo(username.bottom)
                }
        )

        Text(  // post text
            text = stringResource(id = post.textId),
            modifier = Modifier
                .constrainAs(postText) {
                    top.linkTo(profImage.bottom, margin = 16.dp)
                    start.linkTo(profImage.start)
                }
        )
        Image(painter = painterResource(id = post.imageId),
            contentScale = ContentScale.FillBounds,

            contentDescription = "post image",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .constrainAs(postImage) {
                    top.linkTo(postText.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        TextButton(onClick = {},
            colors = ButtonDefaults
                .buttonColors(
                    Color.Transparent,
                    Color.DarkGray,
                    Color.Gray,
                    Color.DarkGray
                ),
            modifier = Modifier
                .constrainAs(likeCount) {
                    top.linkTo(postImage.bottom)
                    start.linkTo(parent.start)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_small_like),
                contentDescription = "like count", tint = Color(1, 102, 226)
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = "112")
        }
        TextButton(onClick = {},
            colors = ButtonDefaults
                .buttonColors(
                    Color.Transparent,
                    Color.DarkGray,
                    Color.Gray,
                    Color.DarkGray
                ),
            modifier = Modifier
                .constrainAs(shareCount) {
                    top.linkTo(likeCount.top)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Share")
            Spacer(modifier = Modifier.padding(2.dp))

            Image(
                painter = painterResource(id = R.drawable.spongebob),
                contentDescription = "profilePicture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)

            )
            Spacer(modifier = Modifier.padding(2.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_drop_down),
                contentDescription = "drop down"
            )
        }
        TextButton(onClick = {},
            colors = ButtonDefaults
                .buttonColors(
                    Color.Transparent,
                    Color.DarkGray,
                    Color.Gray,
                    Color.DarkGray
                ),

            modifier = Modifier
                .constrainAs(like) {
                    start.linkTo(parent.start)
                    top.linkTo(likeCount.bottom)
                    end.linkTo(comment.start)
                }

        ) {

            Icon(painter = painterResource(id = R.drawable.ic_like), contentDescription = "like")
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Like")
        }
        TextButton(onClick = {},
            colors = ButtonDefaults
                .buttonColors(
                    Color.Transparent,
                    Color.DarkGray,
                    Color.Gray,
                    Color.DarkGray
                ),

            modifier = Modifier
                .constrainAs(comment) {
                    top.linkTo(like.top)
                    start.linkTo(like.end)
                    end.linkTo(share.start)
                }


        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "comment"
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Comment")

        }
        TextButton(onClick = {},
            colors = ButtonDefaults
                .buttonColors(
                    Color.Transparent,
                    Color.DarkGray,
                    Color.Gray,
                    Color.DarkGray
                ),

            modifier = Modifier
                .constrainAs(share) {
                    top.linkTo(like.top)
                    end.linkTo(parent.end)
                    start.linkTo(comment.end)
                }

        ) {
            Icon(painter = painterResource(id = R.drawable.ic_share), contentDescription = "share")
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Share")

        }


    }

}

@Composable
fun PostsList() {
    val postsList = mutableListOf(
        Post(R.string.dining, R.drawable.spongebob_cook, R.string.time_1),
        Post(R.string.friends, R.drawable.friends, R.string.time_2),
        Post(R.string.jelly, R.drawable.jelly_sponge, R.string.time_3)
    )
    for (i in 0..2) {
        postsList.addAll(postsList)
    }
    LazyColumn {
        items(postsList.size) {
            ItemPost(postsList[it])
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PostsListPreview() {
    ComposeFacebookPostsTheme {
        PostsList()
    }
}