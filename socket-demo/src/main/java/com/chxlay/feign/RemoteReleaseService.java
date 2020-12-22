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
import com.chxlay.entity.Answer;
import com.chxlay.entity.CallTalk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengleng
 * @date 2018/6/22
 */
@FeignClient(contextId = "remoteReleaseService", value = ServiceName.RELEASE_SERVICE)
public interface RemoteReleaseService {

	/**
	 * 变更答疑题目数据
	 *
	 * @param answer
	 * @param from
	 * @return
	 */
	@PutMapping(value = "/tutor/changeAnswer")
	R<Boolean> changeAnswer(@RequestBody Answer answer, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 云课堂连接变更
	 *
	 * @param callTalk
	 * @param from
	 * @return
	 */
	@PutMapping("/tutor/teaching")
	R<Boolean> changeConnect(@RequestBody CallTalk callTalk, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 结算答疑
	 *
	 * @param answerId
	 * @param from
	 * @return
	 */
	@PostMapping("/tutor/settle")
	R<Boolean> settle(@RequestParam("answerId") String answerId, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询答疑的状态
	 *
	 * @param answerId
	 * @param from
	 * @return
	 */
	@GetMapping(value = "/tutor/getAnswer")
	R<Answer> getAnswer(@RequestParam("answerId") String answerId, @RequestHeader(SecurityConstants.FROM) String from);
}