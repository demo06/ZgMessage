package funny.buildapp.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AppViewModel() {
    private val _chatContent = MutableStateFlow("")
    val chatContent: Flow<String>
        get() = _chatContent

    private val _roomName = MutableStateFlow("上班不摸鱼 脑子有问题")
    val roomName: Flow<String>
        get() = _roomName

    private val _onlineNumber = MutableStateFlow(99)
    val onlineNumber: Flow<Int>
        get() = _onlineNumber

    private val _userName = MutableStateFlow("")
    val userName: Flow<String>
        get() = _userName

    private val _nameDialogStatus = MutableStateFlow(false)
    val nameDialogStatus: Flow<Boolean>
        get() = _nameDialogStatus
}