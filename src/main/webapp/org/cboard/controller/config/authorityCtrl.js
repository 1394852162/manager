/**
 * Created by 陶鹏飞 on 2018/2/6.
 */
cBoard.controller("authorityCtrl",function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter) {
    var translate = $filter('translate');

    var translate = $filter('translate');
    $scope.optFlag = 'none'; //editAuth
    $scope.editAuth = {};

    /**
     * 修改项保存
     */
    $scope.saveEdit = function () {
        $http({
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            url: './employee/updateDeptEmpTree.do',
            data: JSON.stringify({
                EmpId: parseInt($scope.curEmpId),
                DeptId: parseInt($scope.curDeptId),
                EmpStatus3: $scope.editAuth.editEmpStatus3,
                EmpStatus4: $scope.editAuth.editEmpStatus4,
                EmpStatus5: $scope.editAuth.editEmpStatus5,
                EmpStatus6: $scope.editAuth.editEmp1Status6,
                EmpStatus7: $scope.editAuth.editDeptStatus7,
                EmpStatus8: $scope.editAuth.editSearchTicketStatus8,
                EmpStatus9: $scope.editAuth.editEmpStatus9
            })
        }).success(function (response) {
            if (response.code === 0) {
                ModalUtils.alert(translate("设置失败!"), "modal-danger", "md");
            } else if (response.code === 1) {
                ModalUtils.alert(translate("设置成功!"), "modal-success", "md");
                $scope.treeStatusArea = response.code;
            } else if (response.code === -1) {
                ModalUtils.alert(translate("设置失败!"), "modal-danger", "md");
            }
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
    }

    /**
     * 取消
     */
    $scope.canel = function () {
        $scope.optFlag = 'none';
    }
})