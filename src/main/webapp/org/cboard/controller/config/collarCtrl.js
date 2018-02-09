/**
 * Created by 陶鹏飞 on 2018/2/6.
 */
cBoard.controller("collarCtrl",function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter) {
    var translate = $filter('translate');

    var getBatList = function () {
        $http({
            method: 'get',
            url: './batch/GetBatList.do'
        }).success(function (response) {
            $scope.batList = response.data;
            console.log($scope.batList);
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

        /*console.log($scope.collarEmpCode);
        $scope.collarEmpCode = 1;*/
        $http({
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            url: './collar/insertCollarMap.do',
            data: JSON.stringify({
                CollNo: $scope.collarCode,
                BatId: $scope.collarBatchName.batId,
                BatEndTime: $scope.collarD1,
                CollTime: $scope.collarD2,
                EmpId: $scope.collarEmpName.empId,
                CollNum: $scope.collarNum,
                CollNote: $scope.collarNote
            })
        }).success(function () {
            ModalUtils.alert(translate("领取成功" + "!"), "modal-success", "md");
            // getUserList();
            // todo
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });

    };

    /**
     * 取消
     */
    $scope.canelCollar = function () {
        console.log("canelCollar");
    };

})