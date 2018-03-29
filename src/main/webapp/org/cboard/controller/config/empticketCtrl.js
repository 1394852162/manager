/**
 * Created by 陶鹏飞 on 2018/2/6.
 */
cBoard.controller("empticketCtrl",function ($rootScope, $scope, $http, dataService, $uibModal, ModalUtils, $filter) {

    var translate = $filter('translate');

    ///表格的头部
    $scope.oTypeInfos = [
        {'id': 'html', 'name': 'HTML'},
        {'id': 'pdf', 'name': 'PDF'},
        {'id': 'excel2003', 'name': 'Excel 2003'},
        {'id': 'excel2007', 'name': 'Excel 2007'}
    ];

    $scope.testTicket = function () {
        $http({
            method: 'POST',
            // headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            // headers: {'Content-Type': 'application/vnd.ms-excel', 'Accept': 'application/vnd.ms-excel'},
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
            $("#report").attr("src", response.PRD_URL);
        }).error(function (XMLHttpRequest, textStatus, errorThrown) {
            ModalUtils.alert(translate(errorThrown + "!"), "modal-danger", "sm");
        });
    }

})