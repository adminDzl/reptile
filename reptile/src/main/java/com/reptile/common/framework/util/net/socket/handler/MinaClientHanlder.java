package com.reptile.common.framework.util.net.socket.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.reptile.common.framework.util.net.dto.RequestHeadDto;
import com.reptile.common.framework.util.net.dto.SocketRequestDto;
import com.reptile.common.framework.util.net.dto.SocketResponseDto;
import com.reptile.common.framework.util.net.dto.body.RequestBodyDto;
import com.reptile.common.vo.Json;

/***
 * *
 * 类名称：		MinaClientHanlder.java 
 * 类描述：   		
 * 创建人：		
 * 创建时间：		2016-9-7上午10:26:32 
 * 修改人：		liuxing
 * 修改时间：		2016-9-7上午10:26:32 
 * 修改备注：   		
 * @version
 */
public class MinaClientHanlder extends IoHandlerAdapter{  
    
  public void sessionOpened(IoSession session) throws Exception {  
	  SocketRequestDto socketRequestDto = new SocketRequestDto( new RequestHeadDto() , new RequestBodyDto() );  
	  session.write( socketRequestDto );  
	  //长连接则不断在此处发声数据触发，往上层发送数据
  }
    
  @Override  
  public void messageReceived(IoSession session, Object message)  
          throws Exception {  
	  SocketResponseDto js = (SocketResponseDto) message;          
      System.out.println("Server Say: message :" + js.getResponseHeadDto().getMsg() );  
      session.close( true ) ;		//-----短链接可以立即关闭----长连接则设定条件在关闭---此处为立即关闭
  }  
    
  @Override  
  public void sessionClosed(IoSession session) throws Exception {  
      // TODO Auto-generated method stub  
      session.close();  
  }  

}
