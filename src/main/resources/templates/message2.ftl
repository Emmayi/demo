<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>消息通知</title>
</head>

<body>
<div>
    <p>${(params.name)}你好，请准备下一次的设备定期检测任务：</p>
    <table id="inspectionPlan" border="1" cellspacing="0">
        <tr>
            <td>设备定期检测计划Id</td>
            <td>${(params.id)!""}</td>
        </tr>
        <tr>
            <td>设备检测人</td>
            <td>${(params.name)!""}</td>
        </tr>
        <tr>
            <td>检测内容</td>
            <td>${(params.content)!""}</td>
        </tr>
        <tr>
            <td>检测周期</td>
            <td>${(params.period)!""}</td>
        </tr>
        <tr>
            <td>上次检测时间</td>
            <td>${(params.last_time)!""}</td>
        </tr>
    </table>
</div>
</body>
</html>