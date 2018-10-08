$(function () {

    //1.初始化Table
    var oTable = new TableInit();


    oTable.Init();

    //2.初始化Button的点击事件
    // var oButtonInit = new ButtonInit();
    // oButtonInit.Init();

});



var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table

    oTableInit.Init = function () {
        $("#tb_departments").bootstrapTable({
            url: '../dcsConfigIndex',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            // toolbar: '#toolbar',                //工具按钮用哪个容器
            idField: "deviceId",
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 50,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 1000,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            // cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [
                // {
                // checkbox: false
                // },
                {
                    field: 'deviceId',
                    title: '编号',
                    visible:false
                },
                generatorColumn('deviceNum', '编号', true),
                generatorColumn('deviceName', '设备名称', true),
                generatorColumn('deviceAddress', '设备地址', true),
                generatorColumn('devicePort', '端口号', true),
                generatorColumn('deviceBoundRate', '波特率', true),
                generatorColumn('deviceFlowControlIn', 'controlIn', true),
                generatorColumn('deviceFlowControlOut', 'controlOut', true),
                generatorColumn('deviceDataBits', '数据位', true),
                generatorColumn('deviceStopBits', '停止位', true),
                {
                    field: 'deviceParity',
                    title: '奇偶校验',
                    editable: {
                        type: 'text',
                        title: '奇偶校验',
                        validate: function (v) {
                            if (!v)
                                return '不能为空';
                        }
                    }
                },
                generatorColumn('deviceLinkState', '连接状态', false),
                generatorColumn('deviceNote', '备注', true)
            ],
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "get",
                    url: "../dcsConfigUpdate",
                    data: row,
                    dataType: 'JSON',
                    success: function (data, status) {
                        if (status === "success") {
                            alert('提交数据成功');
                        }
                    },
                    error: function () {
                        alert('编辑失败');
                    },
                    complete: function () {
                   //     alert("complete！");
                    }
                });
            }
        })
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            departmentname: $("#txt_search_departmentname").val(),
            statu: $("#txt_search_statu").val()
        };
        return temp;
    };
    return oTableInit;
};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit;
};