package cn.edu.bupt.demo.controller;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.task.client.ClientFutureTask;
import cn.bupt.edu.base.task.client.ClientTask;
import cn.bupt.edu.base.util.RPCUUID;
import cn.bupt.edu.client.datadispatch.ClientTaskMap;
import cn.edu.bupt.demo.channel.Client;
import cn.edu.bupt.demo.protobuf.DeviceProto;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import io.swagger.annotations.Api;
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

    HttpLogin httpLogin = new HttpLogin();


    @RequestMapping(value = "/allDevice", method = RequestMethod.GET)
    @ResponseBody
    public String findAllDevice() throws Exception {

        JSONObject result = new JSONObject();

        try {
            httpLogin.httplogin();

            String device = httpLogin.findDevice();
            JSONArray jsonArray = JSONArray.parseArray(device);
            if (jsonArray.size() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String id = jsonObject.get("id").toString();
                String attributes = httpLogin.findAttributes(id);
                //解析字段返回
                JSONArray ja = JSONArray.parseArray(attributes);
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
        } catch (Exception e) {
            throw new Exception("findAllDevice error!");
        }
    }

    @RequestMapping(value = "/saveDevice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DeviceProto.Device saveDevice(@RequestBody DeviceProto.Device device) {
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
                DeviceProto.Device device = DeviceProto.Device.parseFrom(this.getResp().getBody());
                this.getLogger().info("device name = {}", device.getName());
                return device;
            }
        };
        ClientFutureTask dt = new ClientFutureTask(tc);
        ClientTaskMap.getInstance().putTask(uuid, dt);
        Client.getChannel().writeAndFlush(reqBuilder.build());
        try {
            Object resp = dt.get();
            if (resp instanceof DeviceProto.Device) {
                return (DeviceProto.Device) resp;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
