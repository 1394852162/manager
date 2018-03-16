/**
 * Created by 陶鹏飞 on 2018/2/6.
 */
cBoard.controller("vipCtrl",function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter) {
    var translate = $filter('translate');

    var translate = $filter('translate');
    $scope.optFlag = 'none';
    $scope.dsView = '';
    $scope.curDatasource = {};
    $scope.userName = "";

    ///表格的头部

    $scope.config1 = {
        data: [{id:1,text:'bug'},{id:2,text:'duplicate'},{id:3,text:'invalid'},{id:4,text:'wontfix'}],
        placeholder: '尚无数据'
    };
    $scope.config2 = [
        {id: 6, text: '来自ng-repeat'},
        {id: 7, text: '来自ng-repeat'},
        {id: 8, text: '来自ng-repeat'}
    ];
    $scope.headerInfos = [
        // {'name': 'VIP ID', 'col': 'id'},
        {'name': 'VIP员工', 'col': 'userName'},
        {'name': 'VIP票数', 'col': 'roleName'},
        {'name': '操作人', 'col': 'roleName'},
        {'name': '领取时间', 'col': 'roleName'},
        // {'name': '状态', 'col': 'roleName'},
        {'name': '备注', 'col': 'roleName'},
        {'name': '操作'}
    ];

    $scope.pageSize = 10;　　//分页大小，可以随意更改

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
        $scope.newPages = $scope.pages > 10 ? 10 : $scope.pages;
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
    var getVipList = function () {
        $http({
            method: 'get',
            url: './vip/getVipList.do'
        }).success(function (response) {
            // console.log(response);
            //$scope.userList = response;
            $scope.initPageSort(response);
            $scope.vipNum = response.num;
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
    getVipList();

    /**
     * 数据双向绑定+监听机制
     */
    /*$scope.$watch("vipName", function () {

    })*/


    /**
     * 查询
     */
    $scope.searchVip = function () {
        $http({
            method: 'POST',
            headers : {
                'Content-Type' : 'application/json;charset=UTF-8',
                'Accept': 'application/json'
            },
            url: './vip/QueryNameByList.do',
            data: JSON.stringify({
                EmpName: $scope.vipName,
                BeginDate: $scope.vipD1,
                EndDate: $scope.vipD2
            })
        }).success(function (response) {
            $scope.userList = response;
            $scope.initPageSort($scope.userList);
            $scope.vipNum = response.num;
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        })
    }

    /**
     * 增加
     * @param current
     * @param $event
     */
    $scope.addVip = function (current, $event) {
        $uibModal.open({
            templateUrl: 'org/cboard/view/config/modal/addVip.html',
            //windowTemplateUrl: 'org/cboard/view/util/modal/window.html',
            backdrop: false,
            controller: function ($scope, $uibModalInstance, $http) {
                /**
                 * 员工载入
                 */
                $http({
                    method: 'GET',
                    url: './employee/getEmpList.do'
                }).success(function (response) {
                    $scope.empList = response;
                    // console.log($scope.deptList);
                }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                    ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
                });
                $scope.close = function () {
                    $uibModalInstance.close();
                };
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
                        url: './employee/insertVipTicket.do',
                        data: JSON.stringify({
                            VipEmpID: parseInt($scope.addVipName),
                            VipAddNum: parseInt($scope.addVipTicket),
                            VipAddTime: $scope.addVipDate,
                            VipAddNote: $scope.addVipNote
                        })
                    }).success(function (response) {
                        getVipList();
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
    $scope.delVip = function (current, $event) {
        $http({
            method: 'POST',
            url: './vip/deleteVipTicket.do',
            data: {
                VipId: current.vipAddId
            }
        }).success(function (response) {
            getVipList();
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
    $scope.editVip = function (current, $event) {
        $uibModal.open({
            templateUrl: 'org/cboard/view/config/modal/editVip.html',
            //windowTemplateUrl: 'org/cboard/view/util/modal/window.html',
            backdrop: false,
            controller: function ($scope, $uibModalInstance, $http) {
                $scope.editVipName = current.vipEmpID;
                // console.log($scope.editVipName);
                $scope.editVipTicket = current.vipAddNum;
                $scope.editVipDate = (function () {
                    return new Date(current.vipAddTime).Format("yyyy-MM-dd");
                })();
                $scope.editVipNote = current.vipAddNote;

                /**
                 * 员工载入
                 */
                $http({
                    method: 'GET',
                    url: './employee/getEmpList.do'
                }).success(function (response) {
                    $scope.editVipName = current.vipEmpID;
                    $scope.empList = response.data;
                    // console.log($scope.empList);
                }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                    ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
                });

                $scope.close = function () {
                    $uibModalInstance.close();
                };
                $scope.save = function () {
                    $http({
                        method: 'POST',
                        url: './batch/updateDeptByKey.do',
                        data:{
                            name: $scope.modifyUserName,
                            password: $scope.modifyUserPwd,
                            role: $scope.modifyUserRole,
                            oldRole: current.password,
                            desc: $scope.modifyUserName/*,
                            enabled:current.enabled*/
                        }
                    }).success(function (response) {
                        /*if (response.code === 0) {
                            ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
                        } else if (response.code === 1) {
                            ModalUtils.alert(translate(response.msg + "!"), "modal-success", "md");
                        } else if (response.code === -2) {
                            ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
                        }*/
                        getVipList();
                    }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                        ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
                    });
                    $uibModalInstance.close();
                }
            }
        });
        $event.stopPropagation();//阻止冒泡
    };
});
// angular.bootstrap(document, ['cBoard']);