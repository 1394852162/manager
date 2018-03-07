/**
 * Created by 陶鹏飞 on 2018/2/6.
 */
cBoard.controller("authorityCtrl",function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter) {
    var translate = $filter('translate');

    var translate = $filter('translate');
    $scope.optFlag = 'editAuth';
    $scope.dsView = '';
    $scope.curDatasource = {};
    $scope.userName = "";

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
})