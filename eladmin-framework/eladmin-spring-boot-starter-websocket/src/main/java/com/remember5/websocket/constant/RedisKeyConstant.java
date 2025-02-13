/**
 * Copyright [2022] [remember5]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.remember5.websocket.constant;

/**
 * redis key
 * @author wangjiahao
 * @date 2023/5/6 12:54
 */
public interface RedisKeyConstant {
    /**
     * redis发布订阅topic：发送给指定用户
     */
    String PUSH_MESSAGE_TO_ONE = "PushMessageToOne";

    /**
     * redis发布订阅topic：发送给所有用户
     */
    String PUSH_MESSAGE_TO_ALL = "PushMessageToAll";

    /**
     * websocket 在线用户列表
     */
    String REDIS_WEB_SOCKET_USER_SET = "WEBSOCKET:USER";

}
