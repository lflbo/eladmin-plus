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
package com.remember5.core.constants;

/**
 * 常用静态常量
 *
 * @author Zheng Jie
 * @date 2018-12-26
 */
public interface ElAdminConstant {

    /**
     * 用于IP定位转换
     */
    String REGION = "内网IP|内网IP";
    /**
     * win 系统
     */
    String WIN = "win";

    /**
     * mac 系统
     */
    String MAC = "mac";

    /**
     * 常用接口
     */
    class Url {
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }
}
