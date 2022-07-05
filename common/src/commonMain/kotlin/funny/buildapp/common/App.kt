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
    var text by remember { mutableStateOf("") }
    var roomName by remember { mutableStateOf("上班不摸鱼 脑子有问题") }
    var onlineNumber by remember { mutableStateOf(990) }
    var userName by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val platformName = getPlatformName()
    Box {
        Column(Modifier.padding(20.dp).fillMaxWidth()) {
            //头部
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
            //聊天记录
            Row(Modifier.weight(6f)) {
                TextField(value = text,
                    modifier = Modifier.background(Color.White).fillMaxHeight().weight(8f)
                        .padding(top = 10.dp),
                    readOnly = true,
                    onValueChange = {
                        text = it
                    })
                LazyColumn(
                    modifier = Modifier.weight(2f).padding(top = 10.dp, start = 10.dp).fillMaxHeight()
                        .background(color = Color.Green, shape = RoundedCornerShape(4.dp))
                ) {
                    items(100) {
                        Text("我叫小明", modifier = Modifier.fillMaxWidth().height(20.dp).padding(start = 10.dp))
                    }
                }
            }

            //输入框
            Row(Modifier.weight(2f).fillMaxWidth().padding(top = 10.dp)) {
                TextField(value = text,
                    modifier = Modifier.background(Color.White).fillMaxWidth().fillMaxHeight(),
                    onValueChange = {
                        text = it
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
        if (showDialog) {
            AppDialog(content = "请输入名称",
                modifier = Modifier.align(Alignment.Center),
                onDismiss = { showDialog = false })
        }
    }

}


