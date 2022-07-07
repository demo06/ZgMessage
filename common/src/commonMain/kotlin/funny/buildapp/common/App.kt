package funny.buildapp.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun App() {
    val vm = remember { AppViewModel() }
    val roomName by vm.roomName.collectAsState("")
    val platformName = getPlatformName()

    Box {
        Column(Modifier.padding(20.dp).fillMaxWidth()) {
            //头部
            RoomTitle(roomName, 1)
            Row(Modifier.weight(6f)) {
                //聊天记录
                ChatContent(modifier = Modifier.padding(top = 10.dp).fillMaxHeight().weight(8f), vm)
                //用户列表
                UserList(Modifier.weight(2f), "我是用户")
            }
            //输入框
            InputEdit(Modifier.weight(2f), vm)
        }
        /*if (showDialog) {
            AppDialog(
                content = "请输入名称",
                modifier = Modifier.align(Alignment.Center),
                onDismiss = { *//*showDialog = false*//* })
        }*/
    }

}

@Composable
private fun ChatContent(modifier: Modifier, viewModel: AppViewModel) {
    val content by viewModel.chatContent.collectAsState("")
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    scope.launch {
        state.animateScrollToItem(19)
    }
    LazyColumn(
        state = state,
        modifier = modifier.background(
            color = MaterialTheme.colors.onSurface.copy(alpha = BackgroundOpacity), shape = RoundedCornerShape(4.dp)
        ).padding(10.dp)
    ) {
        items(20) {
            if (it == 19) {
                ChatList(it % 2 == 0, "ahahahahahaahahaahahahahahaahaha")
            } else {
                ChatList(it % 2 == 0, "i say what")
            }

        }
    }
}


@Composable
fun RoomTitle(roomName: String, onlineNumber: Int) {
    Box(Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.CenterStart)) {
            Text(
                text = roomName,
                color = MaterialTheme.colors.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "在线: $onlineNumber 人",
                color = Color.Green,
                fontSize = 12.sp,
            )
        }
    }
}


@Composable
fun ChatList(isMe: Boolean, content: String) {
    if (isMe) {
        Column {
            SelectionContainer {
                Text("小明", color = Color.Red, fontSize = 12.sp)
            }
            SelectionContainer {
                Text(content, modifier = Modifier.padding(bottom = 5.dp))
            }
        }
    } else {
        Column {
            SelectionContainer {
                Text("小明", color = MaterialTheme.colors.primary, fontSize = 12.sp)
            }
            SelectionContainer {
                Text(content, modifier = Modifier.padding(bottom = 5.dp))
            }
        }
    }

}

@Composable
fun UserList(modifier: Modifier, userList: String) {
    Column(
        modifier = modifier.padding(start = 10.dp)
    ) {
        LazyColumn(
            Modifier.fillMaxHeight().padding(top = 10.dp).background(
                color = MaterialTheme.colors.onSurface.copy(alpha = BackgroundOpacity), shape = RoundedCornerShape(4.dp)
            ).padding(top = 10.dp)
        ) {
            items(20) {
                Text(userList, modifier = Modifier.fillMaxWidth().padding(start = 10.dp, bottom = 10.dp))
            }
        }
    }

}


@Composable
fun InputEdit(modifier: Modifier, viewModel: AppViewModel) {
    val roomName by viewModel.roomName.collectAsState("")
    var content by remember { mutableStateOf("") }
    Row(modifier.fillMaxWidth().padding(top = 10.dp)) {
        TextField(value = roomName,
            modifier = Modifier.background(Color.White).fillMaxWidth().fillMaxHeight(),
            onValueChange = {
                viewModel.changeRoomName(it)
//                inputContent = it
                content = it
            },
            label = { Text("userName") },
            trailingIcon = {
                IconButton(onClick = {
//                    showDialog = userName == ""
                    viewModel.appendChat(content)
                }) {
                    Icon(Icons.Filled.Send, null)
                }
            })
    }
}