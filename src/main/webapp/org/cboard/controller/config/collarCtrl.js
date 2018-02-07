/**
 * Created by 陶鹏飞 on 2018/2/6.
 */
cBoard.controller("collarCtrl",function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter) {
    var translate = $filter('translate');

    /**
     * 确定
     */
    $scope.saveCollar = function () {
        console.log("saveCollar");
    };

    /**
     * 取消
     */
    $scope.canelCollar = function () {
        console.log("canelCollar");
    };

})