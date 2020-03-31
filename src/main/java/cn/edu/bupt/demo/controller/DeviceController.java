package cn.edu.bupt.demo.controller;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.task.client.ClientFutureTask;
import cn.bupt.edu.base.task.client.ClientTask;
import cn.bupt.edu.base.util.RPCUUID;
import cn.bupt.edu.client.datadispatch.ClientTaskMap;
import cn.edu.bupt.demo.channel.Client;
import cn.edu.bupt.demo.protobuf.DeviceReqProto;
import cn.edu.bupt.demo.protobuf.DeviceRespProto;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.protobuf.ByteString;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * @author zy
 * @date 2019/5/28 上午11:07
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
@Api(description = "获取设备数据")
public class DeviceController {
    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    HttpLogin httpLogin = new HttpLogin();


    @RequestMapping(value = "/allDevice/{tenantId}", method = RequestMethod.GET)
    @ResponseBody
    public String findAllDevice(@PathVariable("tenantId") String tenantId) throws Exception {

        JSONObject result = new JSONObject();

//        try {
        //httpLogin.httplogin();
        logger.info("***********************************************/api/v1/deviceaccess/tenant/devices/tenantId******************************************");
        JSONObject req = new JSONObject();
        req.put("tenantId", tenantId);
        req.put("limit", 100);
        ProtocolReqMsgProto.ProtocolReqMsg.Builder reqbuilder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
        String uuid = RPCUUID.getUUID();
        reqbuilder.setPath("/api/v1/deviceaccess/tenant/devices/tenantId");
        reqbuilder.addChain("device-access");
        reqbuilder.setVersion(1);
        reqbuilder.setUuid(uuid);
        reqbuilder.setBody(ByteString.copyFrom(JSONObject.toJSONBytes(req)));
        ClientTask tc = new ClientTask() {
            @Override
            public Object call() throws Exception {
                return JSONArray.parse(this.getResp().getBody().toByteArray());
            }
        };
        ClientFutureTask dt = new ClientFutureTask(tc);
        ClientTaskMap.getInstance().putTask(uuid, dt);
        Client.getChannel().writeAndFlush(reqbuilder.build());
        Object obj = dt.get();
        logger.info("get object = {}",obj);
        JSONArray jsonArray = (JSONArray) obj;
//            String device = httpLogin.findDevice();
//            JSONArray jsonArray = JSONArray.parseArray(device);
        if (jsonArray.size() > 0) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String id = jsonObject.get("id").toString();
            JSONObject rreq = new JSONObject();
            rreq.put("deviceId", id);
            // String attributes = httpLogin.findAttributes(id);
            //解析字段返回
            logger.info("***********************************************/api/v1/deviceaccess/data/alllatestdata/deviceId******************************************");
            ProtocolReqMsgProto.ProtocolReqMsg.Builder rreqbuilder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
            String ruuid = RPCUUID.getUUID();
            rreqbuilder.setPath("/api/v1/deviceaccess/data/alllatestdata/deviceId");
            rreqbuilder.addChain("device-access");
            rreqbuilder.setVersion(1);
            rreqbuilder.setUuid(uuid);
            rreqbuilder.setBody(ByteString.copyFrom(JSONObject.toJSONBytes(rreq)));
            ClientTask rtc = new ClientTask() {
                @Override
                public Object call() throws Exception {
                    logger.info("------------------------------------get result-------------------------------------------------");
                    Object obj = JSONArray.parseArray(this.getResp().getBody().toByteArray().toString());
                    logger.info("get result = {}", obj);
                    return obj;
                }
            };
            ClientFutureTask rdt = new ClientFutureTask(tc);
            ClientTaskMap.getInstance().putTask(uuid, rdt);
            Client.getChannel().writeAndFlush(rreqbuilder.build());
            JSONArray ja = (JSONArray) rdt.get();
            //JSONArray ja = JSONArray.parseArray(attributes);
            Iterator<Object> it = ja.iterator();
            while (it.hasNext()) {
                JSONObject ob = (JSONObject) it.next();
                //打印出遍历出的jsonObject
                result.put(ob.get("key").toString(), ob.get("value"));
            }

        } else {
            throw new Exception("data is null");
        }

        return result.toJSONString();
//        } catch (Exception e) {
//            throw new Exception("findAllDevice error!");
//        }
    }

    @RequestMapping(value = "/saveDevice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DeviceRespProto.DeviceResp saveDevice(@RequestBody DeviceReqProto.DeviceReq device) {
        ProtocolReqMsgProto.ProtocolReqMsg.Builder reqBuilder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
        String uuid = RPCUUID.getUUID();
        reqBuilder.setUuid(uuid);
        reqBuilder.setVersion(1);
        reqBuilder.addChain("device-access");
        reqBuilder.setPath("/api/v1/deviceaccess/device");
        reqBuilder.setBody(ByteString.copyFrom(device.toByteArray()));
        ClientTask tc = new ClientTask() {
            @Override
            public Object call() throws Exception {
                DeviceRespProto.DeviceResp device = DeviceRespProto.DeviceResp.parseFrom(this.getResp().getBody());
                this.getLogger().info("device name = {}", device.getName());
                return device;
            }
        };
        ClientFutureTask dt = new ClientFutureTask(tc);
        ClientTaskMap.getInstance().putTask(uuid, dt);
        Client.getChannel().writeAndFlush(reqBuilder.build());
        try {
            Object resp = dt.get();
            if (resp instanceof DeviceRespProto.DeviceResp) {
                return (DeviceRespProto.DeviceResp) resp;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
