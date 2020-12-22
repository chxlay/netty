package com.chxlay.activemq;

import com.chxlay.feign.RemoteReleaseService;
import com.chxlay.talk.AbsTalkHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * @author Alay
 * @date 2020-12-18 11:08
 * @project Braineex
 */
@Component
public class SocketMaConsumer {


    @Autowired
    private RemoteReleaseService releaseService;

    @Qualifier("headTalkHandler")
    @Autowired
    private AbsTalkHandler talkHandler;

/*	@JmsListener(containerFactory = CLOSE_ANSWER_CONNECT_LISTENER, destination = CLOSE_ANSWER_CONNECT)
	public void closeConnect(MapMessage message) {
		try {
			String answerId = message.getString(ANSWER_ID);
			Answer answer = releaseService.getAnswer(answerId, SecurityConstants.FROM_IN).getData();
			// 如果云教室连接还在进行中,则通知客户端已经达到最大欠费时长,进行关闭云教室
			if (StateEnum.START.toString().equals(answer.getState())) {
				CallBackTalk backTalk = CallBackTalk.builder().answerId(answer.getId()).action(SocketConstants.ACTION_CLOSE);
				talkHandler.pushNotice(null, answer.getUserId(), backTalk);
				talkHandler.pushNotice(null, answer.getTeacherUid(), backTalk);
			}
		} catch (JMSException | BrxException e) {
			e.printStackTrace();
		}
	}*/
}