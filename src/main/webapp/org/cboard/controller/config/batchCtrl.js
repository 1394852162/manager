/**
 * Created by 陶鹏飞 on 2018/2/2.
 */
cBoard.controller('batchCtrl', function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter, chartService) {

    var translate = $filter('translate');
    $scope.optFlag = 'none';
    $scope.dsView = '';
    $scope.curDatasource = {};
    $scope.userName = "";

    ///表格的头部

    $scope.headerInfos = [
        {'name': '批次号', 'col': 'id'},
        {'name': '批次名称', 'col': 'userName'},
        {'name': '批次开始时间', 'col': 'roleName'},
        {'name': '批次结束时间', 'col': 'roleName'},
        {'name': '批次总票数', 'col': 'roleName'},
        {'name': '批次对象（在职/离职）', 'col': 'roleName'},
        {'name': '备注', 'col': 'roleName'},
        {'name': '操作'}
    ];

    $scope.pageSize = 8;　　//分页大小，可以随意更改

    /*
     * 当页面列表数据过多时，我们经常会收到将列表内容分页的需求，列表内容分页一般会有两种做法：
     *    1、不需要后台配合，前台一次性拿完所有数据，然后进行分页展示；这种方式只是为了界面上对用户更友好，
     *  并没有实际提升页面的效率（数据量过大时页面加载压力比较大）
     *    2、需要后台配合，后台对改数据做分页处理，页面每次只请求需要展示的该页面的数据，换页时需要二次请求，这种方式是比较推荐的
     */

    /**
     * 分页
     * @param item
     */
    $scope.initPageSort = function (item) {
        // $scope.data = item;
        $scope.data = item.data;
        $scope.pages = Math.ceil($scope.data.length / $scope.pageSize); //分页数
        $scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
        $scope.pageList = [];
        $scope.selPage = 1;
        //设置表格数据源(分页)
        $scope.setData = function () {
            $scope.items = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
        };
        $scope.items = $scope.data.slice(0, $scope.pageSize);
        //分页要repeat的数组
        for (var i = 0; i < $scope.newPages; i++) {
            $scope.pageList.push(i + 1);
        }
        //打印当前选中页索引
        $scope.selectPage = function (page) {
            //不能小于1大于最大
            if (page < 1 || page > $scope.pages) return;
            //最多显示分页数5
            if (page > 2) {
                //因为只显示5个页数，大于2页开始分页转换
                var newpageList = [];
                for (var i = (page - 3); i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)); i++) {
                    newpageList.push(i + 1);
                }
                $scope.pageList = newpageList;
            }
            $scope.selPage = page;
            $scope.setData();
            $scope.isActivePage(page);
        }
    };

    /**
     * 设置当前选中页样式
     * @param page
     * @returns {boolean}
     */
    $scope.isActivePage = function (page) {
        return $scope.selPage == page;
    };

    /**
     * 上一页
     * @constructor
     */
    $scope.Previous = function () {
        $scope.selectPage($scope.selPage - 1);
    };

    /**
     * 下一页
     * @constructor
     */
    $scope.Next = function () {
        $scope.selectPage($scope.selPage + 1);
    };

    // 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")   ==> 2006-7-2 8:9:4.18
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    /**
     * 初始化
     */
    var getBatchList = function () {
        $http({
            method: 'get',
            url: './batch/GetBatList.do'
        }).success(function (response) {
            // console.log(response);
            //$scope.userList = response;
            $scope.initPageSort(response);
            /*
             //淘汰/启用样式控制
             $scope.setStyle = function () {
             //TODO
             }
             */
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
    };
    getBatchList();

    /**
     * 数据双向绑定+监听机制
     */
    $scope.$watch("batchName", function () {
        $http({
            method: 'POST',
            url: './batch/queryNameBatList.do',
            data: {
                BatName: $scope.batchName
            }
        }).success(function (response) {
            $scope.userList = response;
            $scope.initPageSort($scope.userList);
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        })
    })

    /**
     * 增加
     * @param current
     * @param $event
     */
    $scope.addBatch = function (current, $event) {
        $uibModal.open({
            templateUrl: 'org/cboard/view/config/modal/addBatch.html',
            //windowTemplateUrl: 'org/cboard/view/util/modal/window.html',
            backdrop: false,
            controller: function ($scope, $uibModalInstance, $http) {
                $scope.close = function () {
                    $uibModalInstance.close();
                };
                $scope.save = function () {
                    $http({
                        method: 'POST',
                        headers : {
                            'Content-Type' : 'application/json;charset=UTF-8',
                            'Accept': 'application/json'
                        },
                        url: './batch/insertBatch.do',
                        data: JSON.stringify({
                            BatNo: $scope.addBatchNo,
                            BatName: $scope.addBatchName,
                            BatBeginTime: $scope.addBatBeginTime,
                            BatEndTime: $scope.addBatEndTime,
                            BatTicketNum: parseInt($scope.addBatchTicketNum),
                            Status2: $scope.addBatchStatus,
                            BatNote: $scope.addBatchNote,
                        })
                    }).success(function (response) {
                        getBatchList();
                    }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                        ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
                    });
                    $uibModalInstance.close();
                }
            }
        });
        // $event.stopPropagation();//阻止冒泡
    };


    /**
     * 删除
     * @param current
     * @param $event
     */
    $scope.delBatch = function (current, $event) {
        $http({
            method: 'POST',
            url: './batch/deleteBatch.do',
            data: {
                BatId: current.batId
            }
        }).success(function (response) {
            /*if (response.code === 1) {
                ModalUtils.alert(translate(response.msg + "!"), "modal-success", "md");
            } else if (response.code === 0) {
                ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
            } else if (response.code === -1) {
                ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
            } else if (response.code === -2) {
                ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
            }*/
            getBatchList();
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
        $event.stopPropagation();//阻止冒泡
    };

    /**
     * 修改
     * @param current
     * @param $event
     */
    $scope.editBatch = function (current, $event) {
        $uibModal.open({
            templateUrl: 'org/cboard/view/config/modal/editBatch.html',
            //windowTemplateUrl: 'org/cboard/view/util/modal/window.html',
            backdrop: false,
            controller: function ($scope, $uibModalInstance, $http) {

                $scope.editBatchNo = current.batNo;
                $scope.editBatchName = current.batName;
                $scope.editBatBeginTime = (function () {
                    return new Date(current.batBeginTime).Format("yyyy-MM-dd");
                    // var date = new Date(current.batBeginTime).Format;
                    // return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
                })();
                $scope.editBatEndTime = (function () {
                    return new Date(current.batEndTime).Format("yyyy-MM-dd");
                })();
                $scope.editBatchTicketNum = current.batTicketNum;
                $scope.editBatchStatus = current.status;
                $scope.editBatchNote = current.batNote;

                $scope.close = function () {
                    $uibModalInstance.close();
                };
                $scope.save = function () {
                    $http({
                        method: 'POST',
                        url: './batch/updateDeptByKey.do',
                        data:{
                            BatId: current.batId,
                            BatNo: $scope.editBatchNo,
                            BatName: $scope.editBatchName,
                            BatBeginTime: $scope.editBatBeginTime,
                            BatEndTime: $scope.editBatEndTime,
                            BatTicketNum:$scope.editBatchTicketNum,
                            Status2: $scope.editBatchStatus,
                            BatNote: $scope.editBatchNote,
                        }
                    }).success(function (response) {
                        /*if (response.code === 0) {
                            ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
                        } else if (response.code === 1) {
                            ModalUtils.alert(translate(response.msg + "!"), "modal-success", "md");
                        } else if (response.code === -2) {
                            ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
                        }*/
                        getBatchList();
                    }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                        ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
                    });
                    $uibModalInstance.close();
                }
            }
        });
        $event.stopPropagation();//阻止冒泡
    };

    /**
     * 状态
     * @param current
     * @param $event
     */
    $scope.enableBatch = function (current, $event) {
        $http({
            method: 'POST',
            url: './batch/updateUser.do',
            data: {
                name: current.userName,
                enabled: !current.enabled
            }
        }).success(function (response) {
            /*if (response.code === 1) {
                ModalUtils.alert(translate(response.msg + "!"), "modal-success", "md");
            } else if (response.code === 0) {
                ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
            } else if (response.code === -1) {
                ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
            } else if (response.code === -2) {
                ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
            }*/
            getBatchList();
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
        $event.stopPropagation();//阻止冒泡
    };
});