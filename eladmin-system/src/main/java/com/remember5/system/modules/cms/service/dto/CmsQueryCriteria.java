/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.remember5.system.modules.cms.service.dto;

import lombok.Data;
import com.remember5.core.annotation.Query;
import com.remember5.system.modules.column.domain.CmsColumn;

import java.util.List;

/**
 * @author zhangenrong
 * @website https://el-admin.vip
 * @date 2021-03-01
 **/
@Data
public class CmsQueryCriteria {

    /**
     * 栏目
     */
    @Query
    private Long columnId;
    @Query(propName = "id", type = Query.Type.IN)
    private List<Long> ids;
    @Query
    private CmsColumn column;

    /**
     * 作者
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String author;

    /**
     * 文章状态
     */
    @Query
    private Integer cmsStatus;

    /**
     * 审核状态
     */
    @Query
    private Integer auditStatus;

    @Query
    private Long agentId;

    @Query
    private Boolean isDeleted = false;

}
