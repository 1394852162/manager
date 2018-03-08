/**
 * Created by 陶鹏飞 on 2018/3/8.
 */

cBoard.directive('auth', ['$http', '$interval', '$filter', '$log', function ($http, $interval, $filter, $log) {
    return {
        require: '?ngModel',
        restrict: 'A',
        link: function ($scope, element, attrs, ngModel) {
            var setting = {
                view: {
                    dblClickExpand: dblClickExpand,
                    addDiyDom: addDiyDom,
                    selectedMulti: false
                },
                data: {
                    key: {
                        title: "desc"
                    },
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: function (event, treeId, treeNode, clickFlag) {
                        $scope.$apply(function () {
                            ngModel.$setViewValue(treeNode);
                        });
                        // $scope.optFlag = 'editAuth';
                    }
                }
            };
            function dblClickExpand(treeId, treeNode) {
                return treeNode.level > 0;
            };
            function addDiyDom(treeId, treeNode) {
                var aObj = $("#" + treeNode.tId + "_a");
                if ($("#diyBtn_" + treeNode.id).length > 0) return;
                /*var editStr = "<span class='addTree button add' id='addBtn_" + treeNode.id + "' title='增加' onfocus='this.blur();'></span>"
                    + "<span class='addTree button edit' id='editBtn_" + treeNode.id + "' title='修改' onfocus='this.blur();'></span>"
                    + "<span class='addTree button remove' id='delBtn_" + treeNode.id + "' title='删除' onfocus='this.blur();'></span>";*/
                var editStr = "<span class='addTree button edit' id='editBtn_" + treeNode.id + "' title='设置权限' onfocus='this.blur();'></span>";
                aObj.append(editStr);
                // var addbtn = $("#addBtn_" + treeNode.id);
                var editbtn = $("#editBtn_" + treeNode.id);
                // var delbtn = $("#delBtn_" + treeNode.id);
                /*if (addbtn) addbtn.bind("click", function () {
                    $scope.$apply(function () {
                        $scope.curArea = treeNode.name;
                        //提交父ID参数
                        $scope.pId = treeNode.id;
                    });
                    $scope.addArea.Code = "";
                    $scope.addArea.Name = "";
                    $scope.addArea.Desc = "";
                    $scope.optFlagArea = 'addArea';
                });*/
                if (editbtn) editbtn.bind("click", function () {
                    $scope.$apply(function () {
                        //提交父ID参数
                        $scope.curEmpId = treeNode.id;
                        $scope.curDeptId = (function () {
                            return treeNode.pId.split("-")[0];
                        })();
                        $scope.editAuth.EmpId = treeNode.id;
                        $scope.editAuth.EmpName = treeNode.name;
                    });
                    $scope.optFlag = 'editAuth';
                });
                /*if (delbtn) delbtn.bind("click", function () {
                    $scope.optFlagArea = 'none';
                    $scope.searchAreaTree(treeNode);
                    $scope.delArea();
                });*/
            };
            var reloadAreaTree = function () {
                $http({
                    method: 'get',
                    url: './employee/getDeptEmpTree.do'
                }).success(function (response) {
                    // console.log(response.data);
                    var zNodes = [];
                    zNodes = _.map(response.data, function (obj, iteratee, context) {
                        var newArr = [];
                        newArr.push({
                            "id": obj.EmpId,
                            "pId": obj.pId,
                            "name": obj.EmpName,
                            "empNo": obj.EmpNo,
                            "empStatus3": obj.EmpStatus3,
                            "empStatus4": obj.EmpStatus4,
                            "level": obj.Lev,
                            "open": false
                        });
                        return newArr[0];
                    });
                    $.fn.zTree.init(element, setting, zNodes);
                })
            };
            reloadAreaTree();
            //监听的数据是一个函数，该函数必须先在父作用域定义
            $scope.$watch("treeStatusArea", function (newValue, oldValue, $scope) {
                if (newValue && !oldValue) {
                    reloadAreaTree();
                    $scope.treeStatusArea = "";
                }
            }, true);
        }
    }
}]);
