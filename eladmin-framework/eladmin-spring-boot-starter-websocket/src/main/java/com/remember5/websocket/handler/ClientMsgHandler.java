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
package com.remember5.websocket.handler;

import com.remember5.websocket.constant.RedisKeyConstant;
import com.remember5.websocket.properties.NettyProperties;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 接收客户端消息的handler
 *
 * @author wangjiahao
 * @date 2022/12/13 17:30
 */
@Slf4j
@ChannelHandler.Sharable
public class ClientMsgHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private RedisTemplate<String, Object> redisTemplate;

    public ClientMsgHandler(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务器收到消息：{}", msg.text());
        // todo something about
        // 回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器连接成功！"));
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved 被调用" + ctx.channel().id().asLongText());
        // 删除通道
        removeUserId(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("异常：{}", cause.getMessage());
        removeUserId(ctx);
        ctx.close();
//        cause.printStackTrace();
//        ctx.channel().close();
    }


    private void removeUserId(ChannelHandlerContext ctx) {
        NettyProperties.getChannelGroup().remove(ctx.channel());

        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        NettyProperties.getUserChannelMap().remove(userId);
        redisTemplate.opsForSet().remove(RedisKeyConstant.REDIS_WEB_SOCKET_USER_SET, userId);

        ctx.channel().close();
    }
}
