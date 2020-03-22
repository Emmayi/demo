<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>消息通知</title>
</head>

<body>

<div>
    <p>${params.inspection_person}你好，你有新的巡检计划表如下：</p>
    <table id="inspectionPlan" border="1" cellspacing="0">
        <tr>
            <td>巡检计划Id</td>
            <td>${(params.id)}</td>
        </tr>
        <tr>
            <td>巡检人</td>
            <td>${(params.inspection_person)}</td>
        </tr>
        <tr>
            <td>巡检日期</td>
            <td>${(params.inspection_date)?number_to_datetime?string('yyyy-MM-dd')}</td>
        </tr>
        <tr>
            <td>巡检内容</td>
            <td>${(params.content)}</td>
        </tr>
    </table>
    <p>请按时完成巡检，祝一切顺利！</p>
</div>
</body>
</html>