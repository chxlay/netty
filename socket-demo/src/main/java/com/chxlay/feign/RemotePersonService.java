/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.chxlay.feign;

import com.chxlay.common.result.R;
import com.chxlay.constants.SecurityConstants;
import com.chxlay.constants.ServiceName;
import com.chxlay.entity.SimpleInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collection;
import java.util.List;

/**
 * @author lengleng
 * @date 2018/6/22
 */
@FeignClient(contextId = "remotePersonService", value = ServiceName.PERSON_SERVICE)
public interface RemotePersonService {


	/**
	 * 获取用户认证级别个人信息
	 *
	 * @param userIds
	 * @param from
	 * @return
	 */
	@PostMapping("/personinformation/simpleInfos")
	R<List<SimpleInfo>> simpleInfos(@RequestBody Collection<String> userIds,
									@RequestHeader(SecurityConstants.FROM) String from);
	
}