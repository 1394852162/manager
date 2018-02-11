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
            // console.log($scope.empList);
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
    };
    getEmpList();

    $scope.collarEmpChange = function(collarEmpName){
        // $scope.select2=$scope.select1.gnum[0];
        // console.log($scope.collarEmpName);
        $scope.collarEmpCode = $scope.collarEmpName.empId;
    };

    /**
     * 确定
     */
    $scope.saveCollar = function () {

        $http({
            method: 'POST',
            // headers: {'Content-Type': 'application/json;charset=UTF-8', 'Accept': 'application/json'},
            /*headers : {
                'Content-Type' : 'application/json;charset=UTF-8',
                'Accept': 'application/json'
            },*/
            // dataType: 'json',
            url: './employee/getBatEmpInfo.do',
            data: {
                BatId: $scope.collarBatchName.batId,
                EmpId: $scope.collarEmpName.empId
            }
        }).success(function (response) {
            // return response;
            //console.log(response.data[0].standbyticket);
            /*if (response.code === 0) {
                ModalUtils.alert(translate(response.data + "!"), "modal-danger", "md");
            } else if (response.code === 1) {
                ModalUtils.alert(translate(response.data[0].standbyticket), "modal-success", "md");
            }*/
            // var t = response.data[0].standbyticket + parseInt($scope.collarNum);
            // if (parseInt(response.data[0].standbyticket) < 0) {
            if ( parseInt(response.data[0].standbyticket)  > 0
                && parseInt($scope.collarNum) <= parseInt(response.data[0].standbyticket) ) {
                $http({
                    method: 'POST',
                    // headers: {'Content-Type': 'application/json;charset=UTF-8', 'Accept': 'application/json'},
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
                        CollNum: parseInt($scope.collarNum),
                        CollNote: $scope.collarNote
                    })
                }).success(function () {
                    ModalUtils.alert(translate("领取成功" + "!"), "modal-success", "md");
                    // getUserList();
                    // todo
                }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                    ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
                });
                // ModalUtils.alert(response.data[0].standbyticket, "modal-success", "md");
                // alert("YES");
            } else if( parseInt(response.data[0].standbyticket) <= 0 ){
                if ( parseInt(response.data[0].standbyticket) === 0 ){
                    ModalUtils.alert( parseInt($scope.collarNum), "modal-danger", "md");
                } else {
                    ModalUtils.alert( parseInt($scope.collarNum) + parseInt(response.data[0].standbyticket)*-1, "modal-danger", "md");
                }
            }
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });

        /*$http({
            method: 'POST',
            // headers: {'Content-Type': 'application/json;charset=UTF-8', 'Accept': 'application/json'},
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
                CollNum: parseInt($scope.collarNum),
                CollNote: $scope.collarNote
            })
        }).success(function () {
            ModalUtils.alert(translate("领取成功" + "!"), "modal-success", "md");
            // getUserList();
            // todo
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
*/
    };

    /**
     * 取消
     */
    $scope.canelCollar = function () {

    };

})