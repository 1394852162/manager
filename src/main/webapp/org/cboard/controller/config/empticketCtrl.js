/**
 * Created by 陶鹏飞 on 2018/2/6.
 */
cBoard.controller("empticketCtrl",function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter) {

    var translate = $filter('translate');
    $scope.optFlag = 'none'; //editAuth
    $scope.editAuth = {};


    $scope.testTicket = function () {
        $http({
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            // headers: {'Content-Type': 'application/vnd.ms-excel', 'Accept': 'application/vnd.ms-excel'},
            // url: './reporter/getReporter',
            url: './getReporter',
            dataType:"json",
            data: JSON.stringify({
                Title: $scope.prdName,
                StartDay : $scope.prdD1,
                EndDay : $scope.prdD2,
                outputType : $scope.outputType
            })
        }).success(function (response) {
            console.log(response);
            // $("#report").attr("src", response);
            // $("#report").attr("src", "http://192.168.126.146:8080/manager/out/Reporter.html");
            /*if (response.code === 0) {
                ModalUtils.alert(translate("设置失败!"), "modal-danger", "md");
            } else if (response.code === 1) {
                ModalUtils.alert(translate("设置成功!"), "modal-success", "md");
                $scope.treeStatusArea = response.code;
            } else if (response.code === -1) {
                ModalUtils.alert(translate("设置失败!"), "modal-danger", "md");
            }*/
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
    }

})