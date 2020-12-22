package client;

import com.pig4cloud.pigx.search.entity.vo.TeacherEsInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Alay
 * @date 2020-12-04 16:40
 * @project Braineex
 */
public class ClientHandler extends SimpleChannelInboundHandler<TeacherEsInfo> {


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		TeacherEsInfo teacherEsInfo = new TeacherEsInfo();
		teacherEsInfo.setVitae("客户端发送的老师信息");
		ctx.channel().writeAndFlush(teacherEsInfo);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TeacherEsInfo msg) throws Exception {
		System.out.println(msg);
		TeacherEsInfo teacherEsInfo = new TeacherEsInfo();
		teacherEsInfo.setVitae("客户端发送的老师信息");
		ctx.channel().writeAndFlush(teacherEsInfo);
	}
}
