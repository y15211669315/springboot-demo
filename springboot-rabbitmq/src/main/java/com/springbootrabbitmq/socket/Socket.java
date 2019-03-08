package com.springbootrabbitmq.socket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@Component
@ServerEndpoint("/sk/{username}")
public class Socket {


    public static Vector<Socket> vector = new Vector();

    private Session session;

    private String username;

    @OnOpen
    public void open(Session session, @PathParam("username") String username) throws IOException, EncodeException {
        this.session = session;
        this.username = username;
        vector.addElement(this);
        findNum();
    }

    @OnClose
    public void close() throws IOException, EncodeException {
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).getSession().getId().equals(session.getId())) {
                vector.remove(i);
                break;
            }
        }
        findNum();
    }

    private void findNum() throws IOException, EncodeException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", vector.size());
        map.put("names", getNames());
        this.session.getBasicRemote().sendText(JSON.toJSONString(map));
    }

    private List getNames() {
        List names = new ArrayList<String>();
        Iterator<Socket> its = Socket.vector.iterator();
        while (its.hasNext()) { names.add(its.next().getUsername()); }
        return names;
    }

    public Session getSession() {
        return session;
    }

    public String getUsername() {
        return username;
    }
}
