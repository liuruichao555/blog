<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>demo</title>
    <meta http-equiv="Expires" CONTENT="0"/>
<meta http-equiv="Cache-Control" CONTENT="no-cache"/>
<meta http-equiv="Pragma" CONTENT="no-cache"/>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
</head>
<body>
    <div id="container"></div>
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
    <script src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>
    <script>
        $(function(){
            var pageIndex = 1;
            var interTime = setInterval(function() {
                $.ajax({
                    url: '/blockdata.json?pageIndex=' + pageIndex,
                    type: 'GET',
                    data: null,
                    dataType: 'json',
                    success: function (data) {
                        if (data.status == 1) {
                            var list = data.data;
                            ++pageIndex;
                            render(list);
                        }
                    }
                });
            }, 1000);
            function render(list) {
                if (!list || list.length <= 0) {
                    window.clearInterval(interTime);
                    return;
                }
                var dataList = [];
                for (var i = 0; i < list.length; i++) {
                    var id = list[i].id;
                    var txLength = list[i].txLength;
                    dataList.push(['', parseInt(txLength)]);
                }
                $('#container').highcharts({
                    chart: {
                        type: 'column',
                        animation: {
                            duration: 1500,
                            easing: 'easeOutBounce'
                        }

                    },
                    title: {
                        text: '北大区块链快速交易Demo'
                    },
                    subtitle: {
                        text: ''
                    },
                    xAxis: {
                        title: {
                            text: '区块高度'
                        },
                        type: 'category',
                        labels: {
                            rotation: -45,
                            style: {
                                fontSize: '13px',
                                fontFamily: 'Verdana, sans-serif'
                            }
                        }
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: '交易数量'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    tooltip: {
                        headerFormat: '',
                        pointFormat: '当前区块中的交易数量: <b>{point.y:.0f}</b>笔'
                    },
                    series: [{
                        name: 'Population',
                        data: dataList,
                        dataLabels: {
                            enabled: true,
                            rotation: -90,
                            color: '#FFFFFF',
                            align: 'right',
                            format: '{point.y:.0f}', // one decimal
                            y: 10, // 10 pixels down from the top
                            style: {
                                fontSize: '13px',
                                fontFamily: 'Verdana, sans-serif'
                            }
                        },
                        animation: false
                    }]
                });
            }
        });
    </script>
</body>
</html>
