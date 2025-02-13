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
package com.remember5.biz.appversion.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author fly
* @date 2023-04-06
**/
@Entity
@Data
@Table(name="app_version")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class AppVersion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键索引")
    private Integer id;

    @Column(name = "app_id")
    @ApiModelProperty(value = "app的唯一标识")
    private String appId;

    @Column(name = "app_name")
    @ApiModelProperty(value = "app的名称")
    private String appName;

    @Column(name = "version")
    @ApiModelProperty(value = "版本号 as 1.0.0")
    private String version;

    @Column(name = "build")
    @ApiModelProperty(value = "构建版本 as 400")
    private Long build;

    @Column(name = "title")
    @ApiModelProperty(value = "版本标题 as 3.0版本隆重登场")
    private String title;

    @Column(name = "info")
    @ApiModelProperty(value = "版本更新内容,主要在app上展示")
    private String info;

    @Column(name = "min_version")
    @ApiModelProperty(value = "最低支持的版本 as 1.0.2")
    private String minVersion;

    @Column(name = "update_type")
    @ApiModelProperty(value = "更新方式 forcibly = 强制更新, solicit = 弹窗确认更新, silent = 静默更新")
    private String updateType;

    @Column(name = "platform")
    @ApiModelProperty(value = "平台 ios/android/app(ios&android)")
    private String platform;

    @Column(name = "wgt_url")
    @ApiModelProperty(value = "wgt")
    private String wgtUrl;

    @Column(name = "apk_url")
    @ApiModelProperty(value = "apk")
    private String apkUrl;

    @Column(name = "published")
    @ApiModelProperty(value = "是否发布 0=未发布 1=发布")
    private Integer published;

    @Column(name = "archived")
    @ApiModelProperty(value = "是否归档 0=未归档 1=归档 可在拦截器判断，归档的版本禁止提供服务")
    private Integer archived;

    @Column(name = "is_deleted")
    @ApiModelProperty(value = "逻辑删除 0=未删除 1=删除")
    private Integer isDeleted;

    @Column(name = "is_latest_release")
    @ApiModelProperty(value = "最新版本 0=不是最新 1=最新")
    private Integer isLatestRelease;

    @Column(name = "update_time")
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @Column(name = "create_time")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    public void copy(AppVersion source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
