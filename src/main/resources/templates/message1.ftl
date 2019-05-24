<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>消息通知</title>
    <script>
        function timestampToTime(timestamp) {
            var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            var Y = date.getFullYear() + '-';
            var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = date.getMinutes() + ':';
            var s = date.getSeconds();
            return Y+M+D+h+m+s;
        }
    </script>

</head>

<!--
<style type="text/css">
    table {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
        border-collapse: collapse;
    }

    td, th {
        font-size: 1em;
        border: 1px solid #5B4A42;
        padding: 3px 7px 2px 7px;
    }

    th {
        font-size: 1.1em;
        text-align: center;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #24A9E1;
        color: #ffffff;
    }
</style>
-->
<body>
<div>
    <p>${params.inspection_person}你好，你有新的巡检计划表如下：</p>
    <table id="inspectionPlan" border="1" cellspacing="0">
        <tr><td>巡检计划Id</td><td>${(params.id)}</td></tr>
        <tr><td>巡检人</td><td>${(params.inspection_person)}</td></tr>
        <tr><td>巡检日期</td><td>${(params.inspection_date)}</td></tr>
        <tr><td>巡检内容</td><td>${(params.content)}</td></tr>
    </table>
    <p>请按时完成巡检，祝一切顺利！</p>
</div>
</body>
</html>