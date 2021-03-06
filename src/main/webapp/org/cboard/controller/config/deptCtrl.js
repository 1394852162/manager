/**
 * Created by 陶鹏飞 on 2017/8/4.
 */
cBoard.controller('deptCtrl', function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter, chartService) {

    var translate = $filter('translate');
/*    $scope.optFlag = 'none';
    $scope.dsView = '';
    $scope.curDatasource = {};
    $scope.userName = "";*/

    ///表格的头部
    $scope.headerInfos = [
        {'name': '部门号', 'col': 'id'},
        {'name': '部门名称', 'col': 'userName'},
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

    /**
     * 初始化
     */
    var getUserList = function () {
        $http({
            method: 'get',
            url: './dept/getDeptList.do'//,
            /*params: {
                userName: $scope.userName
            }*/
        }).success(function (response) {
            console.log(response);
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
    getUserList();

    /**
     * 数据双向绑定+监听机制
     */
    var searchDeptName = function () {
        $scope.$watch("deptName", function () {
            $http({
                method: 'POST',
                url: './dept/getNameQueryList.do',
                data: {
                    DeptName: $scope.deptName
                }
            }).success(function (response) {
                $scope.userList = response;
                $scope.initPageSort($scope.userList);
            }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
            })
        })
    };
    searchDeptName();

    /**
     * 增加
     * @param current
     * @param $event
     */
    $scope.addDept = function (current, $event) {
        $uibModal.open({
            templateUrl: 'org/cboard/view/config/modal/addDept.html',
            //windowTemplateUrl: 'org/cboard/view/util/modal/window.html',
            backdrop: false,
            controller: function ($scope, $uibModalInstance, $http) {
                $scope.close = function () {
                    $uibModalInstance.close();
                };
                $scope.save = function () {
                    $http({
                        method: 'POST',
                        url: './dept/insertDept.do',
                        headers : {
                            'Content-Type' : 'application/json;charset=UTF-8',
                            'Accept': 'application/json'
                        },
                        data: JSON.stringify({
                            DeptNo: $scope.addDeptNo,
                            DeptName: $scope.addDeptName,
                            DeptNote: $scope.addDeptNote
                        })
                    }).success(function (response) {
                        /*if (response.code === 0) {
                            ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
                        } else if (response.code === 1) {
                            ModalUtils.alert(translate(response.msg + "!"), "modal-success", "md");
                        } else if (response.code === -2) {
                            ModalUtils.alert(translate(response.msg + "!"), "modal-danger", "md");
                        }*/
                        getUserList();
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
    $scope.delDept = function (current, $event) {
        $http({
            method: 'POST',
            url: './dept/deleteDept.do',
            data:{
                DeptId: current.deptId
            }
        }).success(function (response) {
            getUserList();
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
    $scope.editDept = function (current, $event) {
        $uibModal.open({
            templateUrl: 'org/cboard/view/config/modal/editDept.html',
            //windowTemplateUrl: 'org/cboard/view/util/modal/window.html',
            backdrop: false,
            controller: function ($scope, $uibModalInstance, $http) {

                $scope.editDeptNo = current.deptNo;
                $scope.editDeptName = current.deptName;
                $scope.editDeptNote = current.deptNote;

                $scope.close = function () {
                    $uibModalInstance.close();
                };
                $scope.save = function () {
                    $http({
                        method: 'POST',
                        url: './dept/updateDeptByKey.do',
                        data:{
                            DeptId: current.deptId,
                            DeptNo: $scope.editDeptNo,
                            DeptName: $scope.editDeptName,
                            DeptNote: $scope.editDeptNote
                        }
                    }).success(function (response) {
                        getUserList();
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