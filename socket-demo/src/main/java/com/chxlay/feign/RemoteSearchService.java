package com.chxlay.feign;

import com.chxlay.common.result.R;
import com.chxlay.constants.SecurityConstants;
import com.chxlay.constants.ServiceName;
import com.chxlay.entity.TeacherEsInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 搜索模块服务
 *
 * @author Alay
 * @date 2020-12-21 12:09
 * @project Braineex
 */
@FeignClient(contextId = "remoteSearchService", value = ServiceName.SEARCH_SERVICE)
public interface RemoteSearchService {

	/**
	 * 更新老师的ES 中的数据
	 *
	 * @param esInfo
	 * @param from
	 * @return
	 */
	@Order
	@Async
	@PutMapping(value = "/teacher/updateTeacher")
	R<Boolean> updateTeacher(@RequestBody TeacherEsInfo esInfo, @RequestHeader(SecurityConstants.FROM) String from);
}