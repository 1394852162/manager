/**
 * Created by 陶鹏飞 on 2018/2/6.
 */
cBoard.controller("collarCtrl",function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter) {
    var translate = $filter('translate');

    ///表格的头部
    $scope.headerInfos = [
        {'name': '部门', 'col': 'id'},
        {'name': '工号', 'col': 'id'},
        {'name': '姓名', 'col': 'userName'},
        {'name': '领用编号', 'col': 'userName'},
        {'name': '发放批次', 'col': 'userName'},
        {'name': '发放日期', 'col': 'userName'},
        {'name': '截止日期', 'col': 'enabled'},
        {'name': '领用日期', 'col': 'enabled'},
        {'name': '发放数量', 'col': 'roleName'},
        {'name': '已领用数量', 'col': 'description'},
        {'name': '剩余数量', 'col': 'enabled'},
        {'name': '领用说明', 'col': 'enabled'},
        {'name': '是/否出票', 'col': 'enabled'},
        {'name': '操作'}
    ];

    $scope.pageSize = 5;　　//分页大小，可以随意更改

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

    var getBatList = function () {
        var d2 = new Date().Format("yyyy-MM-dd");
        // console.log(d2);
        // $scope.collarD2 = d2;

        $http({
            /*method: 'get',
            url: './batch/GetBatList.do'*/
            method: 'POST',
            headers : {
                'Content-Type' : 'application/json;charset=UTF-8',
                'Accept': 'application/json'
            },
            url: './batch/getBatListbyDept.do',
            data: JSON.stringify({
                Status2: parseInt($scope.collarEmpName.split("_")[2])
            })
        }).success(function (response) {
            $scope.batList = response.data;
            console.log($scope.batList);
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
    };


    var getEmpList = function () {
        $http({
            method: 'get',
            url: './employee/selectDeptListBySession.do'
        }).success(function (response) {
            $scope.empList = response.data;
            $scope.deptEmpList = response.data;
            console.log($scope.deptEmpList);
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
    };
    getEmpList();


    $scope.collarEmpChange = function(){
        // $scope.collarDepName = $scope.collarEmpName.deptId;
        console.log($scope.collarEmpName.split("_")[0]);
        $scope.collarDepName = $scope.collarEmpName.split("_")[0];
        getBatList();
    };
    $scope.collarDeptChange = function(collarDepName){
        // $scope.collarDeptId = $scope.collarDepName.deptId;
        // $scope.collarDeptId = $scope.collarDepName;
        $http({
            method: 'POST',
            url: './employee/QueryDeptEmp.do',
            data: {
                // DeptId: collarDepName.deptId
                DeptId: collarDepName
            }
        }).success(function (response) {
            // $scope.deptEmpList = response.data;
            $scope.deptEmpList = response.data;

        })
    };

    $scope.collarCode = (function(){
        return Date.now().toString().substr(Date.now().toString().length-6)
        // return Date.now().toString();
    })();

    /**
     * 确定
     */
    $scope.saveCollar = function () {
        // var t = new Date($scope.collarBatchName.split("_")[1]).Format("yyyy-MM-dd");
        var t = new Date($scope.collarBatchName.split("_")[1]);
        console.log(t);
        $http({
            method: 'POST',
            url: './employee/getBatEmpInfo.do',
            data: {
                // BatId: $scope.collarBatchName.batId,
                BatId: parseInt($scope.collarBatchName.split("_")[0]),
                EmpId: $scope.collarEmpName.split("_")[1]
            }
        }).success(function (response) {
            if ( parseInt(response.data[0].standbyticket)  > 0 ) {
                if(parseInt($scope.collarNum) <= parseInt(response.data[0].standbyticket)){
                    $http({
                        method: 'POST',
                        headers : {
                            'Content-Type' : 'application/json;charset=UTF-8',
                            'Accept': 'application/json'
                        },
                        dataType: 'json',
                        url: './collar/insertCollarMap.do',
                        data: JSON.stringify({
                            CollNo: $scope.collarCode,
                            // BatId: $scope.collarBatchName.batId,
                            BatId: parseInt($scope.collarBatchName),
                            // BatEndTime: $scope.collarD1,
                            /*BatEndTime: (function () {

                                // return new Date($scope.collarD1).Format("yyyy-MM-dd");
                                return new Date($scope.collarBatchName.split("_")[1]);
                            })(),*/
                            CollTime: $scope.collarD2,
                            // EmpId: $scope.collarEmpName.empId,
                            EmpId: parseInt($scope.collarEmpName.split("_")[1]),
                            // DeptId: parseInt($scope.collarDepName),
                            CollNum: parseInt($scope.collarNum),
                            CollNote: $scope.collarNote,
                            Status: parseInt($scope.collarStatus)
                        })
                    }).success(function () {
                        loadListCollar();
                        ModalUtils.alert(translate("领取成功" + "!"), "modal-success", "md");
                        $scope.collarCode = (function(){
                            // return Date.now().toString();
                            return Date.now().toString().substr(Date.now().toString().length-6)
                        })();
                        // $scope.collarBatchName.batId = null;
                        // $scope.collarEmpName.empId = null;
                        // $scope.collarDepName = null;
                        $scope.collarNum = null;
                        $scope.collarNote = null;
                        // $scope.collarStatus = null;

                    }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                        ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
                    });
                } else {
                    // ModalUtils.alert( parseInt($scope.collarNum) - parseInt(response.data[0].standbyticket), "modal-danger", "md");
                    var t = parseInt(response.data[0].standbyticket)
                    ModalUtils.alert( "领取失败！ 您当前批次最多还可领取" + t + "张劵.", "modal-danger", "md");
                }

            } else if( parseInt(response.data[0].standbyticket) <= 0 ){
                if ( parseInt(response.data[0].standbyticket) === 0 ){
                    // ModalUtils.alert( parseInt($scope.collarNum), "modal-danger", "md");
                    ModalUtils.alert( "领取失败！您已经领取完当前批次的所有票卷.", "modal-danger", "md");
                } else {
                    // ModalUtils.alert( parseInt($scope.collarNum) + parseInt(response.data[0].standbyticket)*-1, "modal-danger", "md");
                    ModalUtils.alert( "领取失败！您已经领取完当前批次的所有票卷.", "modal-danger", "md");
                }
            }
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });

    };

    var loadListCollar = function () {
        $http({
            method: 'POST',
            headers : {
                'Content-Type' : 'application/json;charset=UTF-8',
                'Accept': 'application/json'
            },
            url: './collar/getCollarTicketList.do',
            data: JSON.stringify({
                DeptId: parseInt($scope.collarDepName),
                // EmpId: $scope.collarEmpName.empId,
                EmpId: parseInt($scope.collarEmpName.split("_")[1]),
                // BatId: $scope.collarBatchName.batId
                BatId: parseInt($scope.collarBatchName)
            })
        }).success(function (response) {
            // $scope.deptEmpList = response.data;
            $scope.listCollar = response.data;
            $scope.initPageSort(response);

        })
    };

    /**
     * 取消
     */
    $scope.canelCollar = function () {
        loadListCollar();
    };

    /**
     * 删除
     * @param current
     * @param $event
     */
    $scope.delListCollar = function (current, $event) {
        $http({
            method: 'POST',
            url: './collar/deleteCollar.do',
            data: {
                CollId: current.CollId
            }
        }).success(function (response) {
            loadListCollar();
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
    $scope.editListCollar = function (current, $event) {
        $uibModal.open({
            templateUrl: 'org/cboard/view/config/modal/editVerification.html',
            //windowTemplateUrl: 'org/cboard/view/util/modal/window.html',
            backdrop: false,
            controller: function ($scope, $uibModalInstance, $http) {

                $scope.editVerificationTicketNum = current.CollNum;
                $scope.editVerificationNote = current.CollNote;
                $scope.editVerificationTicketD = (function () {
                    return new Date(current.CollTime).Format("yyyy-MM-dd");
                })();

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
                        url: './collar/updateCollar.do',
                        data: JSON.stringify({
                            CollId: current.CollId,
                            CollNum: parseInt($scope.editVerificationTicketNum),
                            CollTime: $scope.editVerificationTicketD,
                            CollNote: $scope.editVerificationNote
                        })
                    }).success(function (response) {
                        loadListCollar();
                    }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                        ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
                    });
                    $uibModalInstance.close();
                }
            }
        });
        $event.stopPropagation();//阻止冒泡
    };

}).filter('unique', function () {
    return function (collection, keyname) {
        var output = [],
            keys = [];
        angular.forEach(collection, function (item) {
            if(item.deptName != null || item.empName != null){
                var key = item[keyname];
                if ( (keys.indexOf(key) === -1) ) {
                    keys.push(key);
                    output.push(item);
                }
            }
        });
        return output;
    };
});