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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
        val (profImage, username, timeText, public,
            option, postText, postImage, likeCount, shareCount,
            like, comment, share) = createRefs()

        Image( // profile image
            painter = painterResource(id = R.drawable.spongebob),
            contentDescription = stringResource(id = R.string.profile_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .constrainAs(profImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text( // username
            text = stringResource(id = R.string.username),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .constrainAs(username) {
                    start.linkTo(profImage.end, margin = 16.dp)
                    top.linkTo(profImage.top, margin = 16.dp)
                }
        )
        Text( // time
            text = stringResource(id = post.timeId),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.constrainAs(timeText) {
                top.linkTo(username.bottom, margin = 8.dp)
                start.linkTo(username.start)
            }
        )
        Icon( // public icon
            painter = painterResource(id = R.drawable.ic_public),
            contentDescription = stringResource(id = R.string.public_icon),
            tint = Color.DarkGray,
            modifier = Modifier
                .size(16.dp)
                .constrainAs(public) {
                    top.linkTo(timeText.top)
                    start.linkTo(timeText.end, margin = 8.dp)
                    bottom.linkTo(timeText.bottom)
                }
        )
        IconButton( // options
            onClick = {},
            colors = IconButtonDefaults
                .iconButtonColors(
                    Color.Transparent,
                    Color.DarkGray,
                    Color.Gray,
                    Color.DarkGray
                ),
            modifier = Modifier
                .size(24.dp)
                .constrainAs(option) {
                    end.linkTo(parent.end)
                    top.linkTo(username.top)
                    bottom.linkTo(username.bottom)
                }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_options),
                contentDescription = stringResource(id = R.string.options)
            )
        }
        Text(  // post text
            text = stringResource(id = post.textId),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .constrainAs(postText) {
                    top.linkTo(profImage.bottom, margin = 16.dp)
                    start.linkTo(profImage.start)
                }
        )
        Image( // post image
            painter = painterResource(id = post.imageId),
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(id = R.string.post_image),
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .constrainAs(postImage) {
                    top.linkTo(postText.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        CustomTextButton( // likes count
            R.string.likes_count, R.drawable.ic_small_like,
            Modifier.constrainAs(likeCount)
            {
                top.linkTo(postImage.bottom)
                start.linkTo(parent.start)
            },
            Color(1, 102, 226),
            R.string.likes_description
        ) {}
        TextButton( // shares count
            onClick = {},
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
        CustomTextButton( // like
            R.string.like, R.drawable.ic_like, Modifier
                .constrainAs(like) {
                    start.linkTo(parent.start)
                    top.linkTo(likeCount.bottom)
                    end.linkTo(comment.start)
                }) {}
        CustomTextButton( // comment
            R.string.comment, R.drawable.ic_comment, Modifier
                .constrainAs(comment) {
                    top.linkTo(like.top)
                    start.linkTo(like.end)
                    end.linkTo(share.start)
                }) {}
        CustomTextButton( // share
            R.string.share, R.drawable.ic_share, Modifier
                .constrainAs(share) {
                    top.linkTo(like.top)
                    end.linkTo(parent.end)
                    start.linkTo(comment.end)
                }) {}
    }
}

@Composable
fun CustomTextButton(
    textId: Int, iconId: Int, modifier: Modifier,
    iconTint: Color = Color.DarkGray,
    descriptionId: Int = textId, onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults
            .buttonColors(
                Color.Transparent,
                Color.DarkGray,
                Color.Gray,
                Color.DarkGray
            ),
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
    )
    {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = stringResource(id = descriptionId),
            tint = iconTint
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = stringResource(id = textId))

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