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
                    addHoverDom: addHoverDom,
                    removeHoverDom: removeHoverDom,
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
                    beforeExpand: beforeExpand,
                    onClick: function (event, treeId, treeNode, clickFlag) {
                        $scope.$apply(function () {
                            ngModel.$setViewValue(treeNode);
                        });
                        // $scope.optFlag = 'editAuth';
                        var editDom = "editBtn_" + treeNode.id;
                        console.log(document.getElementById("editDom"));

                    }
                }
            };
            function dblClickExpand(treeId, treeNode) {
                return treeNode.level > 0;
            };
            function addHoverDom(treeId, treeNode) {
                var aObj = $("#" + treeNode.tId + "_a");
                // if ($("#vipBtn_"+treeNode.id).length>0) return;
                if ($("#diyBtn_"+treeNode.id).length>0) return;
                var editStr = "<span id='diyBtn_space_" +treeNode.id+ "' > </span>"
                    // + "<button type='button' class='diyBtn1 btn btn-danger btn-xs' id='vipBtn_" + treeNode.id + "' title='"+treeNode.name+"' onfocus='this.blur();'>VIP</button>"
                    + "<button type='button' class='diyBtn1 btn btn-danger btn-xs' id='diyBtn_" + treeNode.id + "' title='"+treeNode.name+"' onfocus='this.blur();'>设置权限</button>";
                // var editStr = "<span class='addTree button edit' id='editBtn_" + treeNode.id + "' title='设置权限' onfocus='this.blur();'></span>";
                if(!treeNode.isParent){
                    aObj.append(editStr);
                }
                var btn = $("#diyBtn_"+treeNode.id);
                if (btn) btn.bind("click", function(){
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
                /*var aObj = $("#" + treeNode.tId + "_a");
                if ($("#diyBtn_" + treeNode.id).length > 0) return;
                var editStr = "<span class='addTree button edit' id='editBtn_" + treeNode.id + "' title='设置权限' onfocus='this.blur();'></span>";
                aObj.append(editStr);
                var editbtn = $("#editBtn_" + treeNode.id);
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
                });*/
            };
            function removeHoverDom(treeId, treeNode) {
                $("#diyBtn_"+treeNode.id).unbind().remove();
                $("#diyBtn_space_" +treeNode.id).unbind().remove();
            };
            var curPage = 0;
            function goPage(treeNode, page) {
                treeNode.page = page;
                if (treeNode.page<1) treeNode.page = 1;
                if (treeNode.page>treeNode.maxPage) treeNode.page = treeNode.maxPage;
                if (curPage == treeNode.page) return;
                curPage = treeNode.page;
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.reAsyncChildNodes(treeNode, "refresh");
            }
            function beforeExpand(treeId, treeNode) {
                if (treeNode.page == 0) treeNode.page = 1;
                return !treeNode.isAjaxing;
            }
            function addDiyDom(treeId, treeNode) {
                var aObj = $("#" + treeNode.tId + "_a");
                if ($("#diyBtn_" + treeNode.id).length > 0) return;

                /*var editStr = "<span class='addTree button add' id='addBtn_" + treeNode.id + "' title='增加' onfocus='this.blur();'></span>"
                    + "<span class='addTree button edit' id='editBtn_" + treeNode.id + "' title='修改' onfocus='this.blur();'></span>"
                    + "<span class='addTree button remove' id='delBtn_" + treeNode.id + "' title='删除' onfocus='this.blur();'></span>";
                var editStr = "<span class='addTree button edit' id='editBtn_" + treeNode.id + "' title='设置权限' onfocus='this.blur();'></span>";*/

                var editStr = "<span id='diyBtn_space_auth_" +treeNode.id+ "' > </span>"
                    + "<button type='button' class='diyBtn1 btn btn-primary btn-xs' id='emp1Btn_" + treeNode.id + "' title='"+treeNode.name+"' onfocus='this.blur();'>员工</button>"
                    + "<button type='button' class='diyBtn1 btn btn-primary btn-xs' id='deptBtn_" + treeNode.id + "' title='"+treeNode.name+"' onfocus='this.blur();'>部门</button>"
                    + "<button type='button' class='diyBtn1 btn btn-primary btn-xs' id='searchTicketBtn_" + treeNode.id + "' title='"+treeNode.name+"' onfocus='this.blur();'>核销查询</button>"
                    + "<button type='button' class='diyBtn1 btn btn-primary btn-xs' id='batchBtn_" + treeNode.id + "' title='"+treeNode.name+"' onfocus='this.blur();'>批次</button>"
                    + "<button type='button' class='diyBtn1 btn btn-success btn-xs' id='empBtn_" + treeNode.id + "' title='"+treeNode.name+"' onfocus='this.blur();'>职工劵</button>"
                    + "<button type='button' class='diyBtn1 btn btn-info btn-xs' id='vipBtn_" + treeNode.id + "' title='"+treeNode.name+"' onfocus='this.blur();'>VIP劵</button>";

                var pageStr = "<span class='button lastPage' id='lastBtn_" + treeNode.id
                    + "' title='last page' onfocus='this.blur();'></span><span class='button nextPage' id='nextBtn_" + treeNode.id
                    + "' title='next page' onfocus='this.blur();'></span><span class='button prevPage' id='prevBtn_" + treeNode.id
                    + "' title='prev page' onfocus='this.blur();'></span><span class='button firstPage' id='firstBtn_" + treeNode.id
                    + "' title='first page' onfocus='this.blur();'></span>";
                if(!treeNode.isParent){
                    aObj.append(editStr);
                } else {
                    /*aObj.after(pageStr);
                    var first = $("#firstBtn_"+treeNode.id);
                    var prev = $("#prevBtn_"+treeNode.id);
                    var next = $("#nextBtn_"+treeNode.id);
                    var last = $("#lastBtn_"+treeNode.id);
                    treeNode.maxPage = Math.round(treeNode.count/treeNode.pageSize - .5) + (treeNode.count%treeNode.pageSize == 0 ? 0:1);
                    first.bind("click", function(){
                        if (!treeNode.isAjaxing) {
                            goPage(treeNode, 1);
                        }
                    });
                    last.bind("click", function(){
                        if (!treeNode.isAjaxing) {
                            goPage(treeNode, treeNode.maxPage);
                        }
                    });
                    prev.bind("click", function(){
                        if (!treeNode.isAjaxing) {
                            goPage(treeNode, treeNode.page-1);
                        }
                    });
                    next.bind("click", function(){
                        if (!treeNode.isAjaxing) {
                            goPage(treeNode, treeNode.page+1);
                        }
                    });*/
                }
                if(!(treeNode.empStatus3 === 1)){
                    $("#vipBtn_"+treeNode.id).remove();
                }
                if(!(treeNode.empStatus4 === 1)){
                    $("#empBtn_"+treeNode.id).remove();
                }1
                if(!(treeNode.empStatus5 === 1)){
                    $("#batchBtn_"+treeNode.id).remove();
                }
                if(!(treeNode.empStatus6 === 1)){
                    $("#emp1Btn_"+treeNode.id).remove();
                }
                if(!(treeNode.empStatus7 === 1)){
                    $("#deptBtn_"+treeNode.id).remove();
                }
                if(!(treeNode.empStatus8 === 1)){
                    $("#searchTicketBtn_"+treeNode.id).remove();
                }


                // var addbtn = $("#addBtn_" + treeNode.id);
                // var editbtn = $("#vipBtn_" + treeNode.id);
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
                /*if (editbtn) editbtn.bind("click", function () {
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
                });*/
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
                            "empStatus5": obj.EmpStatus5,
                            "empStatus6": obj.EmpStatus6,
                            "empStatus7": obj.EmpStatus7,
                            "empStatus8": obj.EmpStatus8,
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
