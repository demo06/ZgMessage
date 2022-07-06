package funny.buildapp.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun App() {

    val platformName = getPlatformName()
    Box {
        Column(Modifier.padding(20.dp).fillMaxWidth()) {
            //头部
            RoomTitle(roomName, onlineNumber)

            Row(Modifier.weight(6f)) {
                //聊天记录
                TextField(value = text,
                    modifier = Modifier.background(Color.White).fillMaxHeight().weight(8f).padding(top = 10.dp),
                    readOnly = true,
                    onValueChange = {
                        text = it
                    })
                //用户列表
                UserList(Modifier.weight(2f), "我是用户")
            }
            //输入框
            InputEdit(Modifier.weight(2f))
        }
        if (showDialog) {
            AppDialog(
                content = "请输入名称",
                modifier = Modifier.align(Alignment.Center),
                onDismiss = { showDialog = false })
        }
    }

}


@Composable
fun RoomTitle(roomName: String, onlineNumber: Int) {
    Box(Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.CenterStart)) {
            Text(
                text = roomName,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "在线: $onlineNumber 人",
                color = Color.Green,
                fontSize = 12.sp,
            )
        }
    }
}

@Composable
fun UserList(modifier: Modifier, userList: String) {
    LazyColumn(
        modifier = modifier.padding(top = 10.dp, start = 10.dp).fillMaxHeight()
            .background(color = Color.Green, shape = RoundedCornerShape(4.dp))
    ) {
        items(100) {
            Text(userList, modifier = Modifier.fillMaxWidth().height(20.dp).padding(start = 10.dp))
        }
    }
}

@Composable
fun InputEdit(modifier: Modifier, inputContent: String) {
    Row(modifier.fillMaxWidth().padding(top = 10.dp)) {
        TextField(value = inputContent,
            modifier = Modifier.background(Color.White).fillMaxWidth().fillMaxHeight(),
            onValueChange = {
                inputContent = it
            },
            label = { Text(userName) },
            trailingIcon = {
                IconButton(onClick = {
                    showDialog = userName == ""
                }) {
                    Icon(Icons.Filled.Send, null)
                }
            })
    }
}