/**
 * Created by 陶鹏飞 on 2018/2/6.
 */
cBoard.controller("collarCtrl",function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter) {
    var translate = $filter('translate');

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
        $scope.collarD2 = d2;

        $http({
            method: 'get',
            url: './batch/GetBatList.do'
        }).success(function (response) {
            $scope.batList = response.data;
            // console.log($scope.batList);
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
    };
    getBatList();

    var getEmpList = function () {
        $http({
            method: 'get',
            url: './employee/getEmpList.do'
        }).success(function (response) {
            $scope.empList = response.data;
            $scope.deptEmpList = response.data;
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
    };
    getEmpList();

    $scope.collarEmpChange = function(){
        $scope.collarDepName = $scope.collarEmpName.deptId;
    };
    $scope.collarDeptChange = function(collarDepName){
        $scope.collarDeptId = $scope.collarDepName.deptId;
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
        /*var count = 3000;
        var originalArray = new Array;//原数组
        //给原数组originalArray赋值
        for ( var i=0;i<count;i++ ){
            originalArray[i] = i + 1;
        }
        originalArray.sort(function(){ return 0.5 - Math.random(); });
        for (var i = 0;i < count;i++){
            originalArray[i];
        }
        collarCodeStr = originalArray.toString();
        collarCodeStr = collarCodeStr.replace(",","1").substr(0, 6).replace(",","1");
        return collarCodeStr;*/
        return Date.now().toString().substr(Date.now().toString().length-7)
        //return Date.now().toString();
    })();

    /**
     * 确定
     */
    $scope.saveCollar = function () {
        $http({
            method: 'POST',
            url: './employee/getBatEmpInfo.do',
            data: {
                BatId: $scope.collarBatchName.batId,
                EmpId: $scope.collarEmpName.empId
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
                            BatId: $scope.collarBatchName.batId,
                            // BatEndTime: $scope.collarD1,
                            BatEndTime: (function () {
                                return new Date($scope.collarD1).Format("yyyy-MM-dd");
                            })(),
                            CollTime: $scope.collarD2,
                            EmpId: $scope.collarEmpName.empId,
                            DeptId: $scope.collarDepName,
                            CollNum: parseInt($scope.collarNum),
                            CollNote: $scope.collarNote,
                            Status: parseInt($scope.collarStatus)
                        })
                    }).success(function () {
                        ModalUtils.alert(translate("领取成功" + "!"), "modal-success", "md");
                        $scope.collarCode = (function(){
                            // return Date.now().toString();
                            return Date.now().toString().substr(Date.now().toString().length-7)
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

    /**
     * 取消
     */
    $scope.canelCollar = function () {

    };

}).filter('unique', function () {
    return function (collection, keyname) {
        var output = [],
            keys = [];
        angular.forEach(collection, function (item) {
            if(item.deptName != null){
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