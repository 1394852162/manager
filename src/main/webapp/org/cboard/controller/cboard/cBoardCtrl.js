/**
 * Created by yfyuan on 2016/7/19.
 */
cBoard.controller('cBoardCtrl', function ($rootScope, $scope, $location, $http, $q, $filter, $uibModal, ModalUtils) {

    var translate = $filter('translate');

    $scope.avatar = './dist/img/user-male-circle-blue-128.png';
    /*$scope.verificationFlag = 0;
    $scope.vipFlag = 0;
    $scope.adminFlag = 0;*/
    // $scope.username = "admin";
    // console.log($window.sessionStorage);

    /*$scope.userName = userName;
    console.log($scope.userName);*/
    /*
     $scope.collection = {};
     $scope.collection.p1 = $scope.roleName;
     $scope.collection.p2 = $scope.userName;
     console.log($scope.collection);
     $scope.$watch('collection', function () {
     alert("changed!");
     }, true);
     */

    $http({
        method: 'get',
        url: './employee/getSessionUsername.do'
    }).success(function (response) {
        $scope.collarFlag = parseInt(response.code.empStatus4);
        $scope.vipFlag = parseInt(response.code.empStatus3);
        $scope.vipAddFlag = parseInt(response.code.empStatus9);
        $scope.batchFlag = parseInt(response.code.empStatus5);
        $scope.empFlag = parseInt(response.code.empStatus6);
        $scope.deptFlag = parseInt(response.code.empStatus7);
        $scope.searchTicketFlag = parseInt(response.code.empStatus8);
        $scope.empStatus9Flag = parseInt(response.code.empStatus9);
        $scope.empStatus10Flag = parseInt(response.code.empStatus10);

        $scope.adminFlag = response.code.empName;
        $scope.sessionUser = response.code;

        $scope.sessionUserCode = response.code;
        console.log(response);
    }).error(function (XMLHttpRequest, textStatus, errorThrown) {
        ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
    });

    $scope.changePwd = function (current, $event) {
        if ($scope.sessionUserCode !== 0){
            $uibModal.open({
                templateUrl: 'org/cboard/view/config/modal/changePwd.html',
                windowTemplateUrl: 'org/cboard/view/util/modal/window.html',
                backdrop: false,
                size: 'sm',
                controller: function ($scope, $uibModalInstance) {
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    $scope.ok = function () {

                        $http({
                            method: 'POST',
                            url: './employee/updatepwd.do',
                            data : {
                                // userName : current.name,
                                // oldPassword : $scope.curPwd,
                                password : $scope.newPwd
                            }
                        }).success(function (response) {
                            // ModalUtils.alert(translate(response.msg), "modal-success", "sm");
                            $uibModalInstance.close();
                        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
                        });
                        // document.location.href= "login.html";

                        /*$http.post("commons/changePwd.do", { ./user/updatePassword.do
                            curPwd: $scope.curPwd,
                            newPwd: $scope.newPwd,
                            cfmPwd: $scope.cfmPwd
                        }).success(function (serviceStatus) {
                            if (serviceStatus.status == '1') {
                                ModalUtils.alert(translate("COMMON.SUCCESS"), "modal-success", "sm");
                                $uibModalInstance.close();
                            } else {
                                ModalUtils.alert(serviceStatus.msg, "modal-warning", "lg");
                            }
                        });*/
                    };
                }
            });
        } else {
            alert("请重新登录系统！");
        }
    }
});